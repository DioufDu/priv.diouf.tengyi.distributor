package priv.diouf.tengyi.distributor.persistence.repositories;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.Dish;
import priv.diouf.tengyi.distributor.persistence.models.Dish_;
import priv.diouf.tengyi.distributor.persistence.models.RateGroup_;
import priv.diouf.tengyi.distributor.services.models.AdvancedSearchCriteria;
import priv.diouf.tengyi.distributor.services.models.BasicSearchCriteria;
import priv.diouf.tengyi.distributor.services.models.SearchByDateCriteria;
import priv.diouf.tengyi.distributor.services.models.SearchByLineCriteria;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class DishRepository extends GeneralJpaRepository<Dish, Dish_, Long> {

	/*
	 * Constructors
	 */

	@Autowired
	public DishRepository(EntityManager entityManager) {
		super(Dish.class, entityManager);
	}

	/*
	 * Actions
	 */

	@Transactional(readOnly = true)
	public TreeMap<Calendar, long[]> generateStatistics() {
		// Parameters
		CriteriaBuilder cb = this.sharedEntityManager.getCriteriaBuilder();
		Calendar startDate = Calendar.getInstance();
		startDate.add(Calendar.DATE, -7);
		// Query for dish count
		CriteriaQuery<Tuple> queryDishCount = cb.createTupleQuery();
		Root<Dish> rootDishCount = queryDishCount.from(Dish.class);
		queryDishCount.groupBy(rootDishCount.get(Dish_.offerDate)).having(cb.notEqual(cb.count(rootDishCount), 0L),
				cb.greaterThanOrEqualTo(rootDishCount.get(Dish_.offerDate), startDate)).select(
				cb.tuple(rootDishCount.get(Dish_.offerDate), cb.count(rootDishCount)));
		List<Tuple> queryDishCountResult = this.sharedEntityManager.createQuery(queryDishCount).setMaxResults(10).getResultList();
		// Query for recommendation count
		CriteriaQuery<Tuple> queryRecommendationCount = cb.createTupleQuery();
		Root<Dish> rootRecommendationCount = queryRecommendationCount.from(Dish.class);
		queryRecommendationCount.where(cb.equal(rootRecommendationCount.get(Dish_.isRecommended), true)).groupBy(
				rootRecommendationCount.get(Dish_.offerDate)).having(cb.notEqual(cb.count(rootRecommendationCount), 0L),
				cb.greaterThanOrEqualTo(rootRecommendationCount.get(Dish_.offerDate), startDate)).select(
				cb.tuple(rootRecommendationCount.get(Dish_.offerDate), cb.count(rootRecommendationCount)));

		queryRecommendationCount.orderBy(cb.asc(rootRecommendationCount.get(Dish_.offerDate)));
		List<Tuple> queryRecommendationResult = this.sharedEntityManager.createQuery(queryRecommendationCount).setMaxResults(10)
				.getResultList();

		TreeMap<Calendar, long[]> futureDishStatistics = new TreeMap<Calendar, long[]>();
		for (Tuple tuple : queryDishCountResult) {
			futureDishStatistics.put((Calendar) tuple.get(0), new long[] { (long) tuple.get(1), 0L });
		}
		for (Tuple tuple : queryRecommendationResult) {
			long[] dishStatisticsForDay = futureDishStatistics.get(tuple.get(0));
			if (!ArrayUtils.isEmpty(dishStatisticsForDay)) {
				dishStatisticsForDay[1] = (long) tuple.get(1);
			}
		}
		return futureDishStatistics;
	}

	@Transactional(readOnly = true)
	public Page<Dish> findAllRecommended(PageRequest pageRequest) {
		return this.findAll(new Specification<Dish>() {
			@Override
			public Predicate toPredicate(Root<Dish> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				fetchWithDetails(root, query);
				return cb.and(cb.equal(root.get(Dish_.isRecommended), true), cb.greaterThanOrEqualTo(root.get(Dish_.offerDate), Calendar
						.getInstance()));
			}
		}, pageRequest);
	}

	@Transactional(readOnly = true)
	public Page<Dish> findAllRecent(PageRequest pageRequest) {
		Page<Dish> dishes = this.findAll(new Specification<Dish>() {
			@Override
			public Predicate toPredicate(Root<Dish> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				fetchWithDetails(root, query);
				return cb.greaterThanOrEqualTo(root.get(Dish_.offerDate), Calendar.getInstance());
			}

		}, pageRequest);
		if (dishes.getSize() < pageRequest.getPageSize()) {
			new ArrayList<Dish>(dishes.getContent()).addAll(this.findAll(new Specification<Dish>() {
				@Override
				public Predicate toPredicate(Root<Dish> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					fetchWithDetails(root, query);
					return cb.lessThan(root.get(Dish_.offerDate), Calendar.getInstance());
				}
			}, new PageRequest(0, pageRequest.getPageSize() - dishes.getSize())).getContent());
			return new PageImpl<Dish>(dishes.getContent(), pageRequest, pageRequest.getPageSize());

		} else {
			return dishes;
		}
	}

	@Transactional(readOnly = true)
	public Page<Dish> findAllPopular(PageRequest pageRequest) {
		return this.findAll(new Specification<Dish>() {
			@Override
			public Predicate toPredicate(Root<Dish> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				fetchWithDetails(root, query);
				return cb.notEqual(root.join(Dish_.rateGroup).get(RateGroup_.count), 0);
			}
		}, pageRequest);
	}

	@Transactional(readOnly = true)
	public List<Dish> findAll(SearchByDateCriteria searchByDateCriteria, SearchByLineCriteria searchByLineCriteria) {
		List<Dish> models = this.findAll(generateSearchByDateAndLineQueryPredicate(searchByDateCriteria, searchByLineCriteria));
		return models;
	}

	@Transactional(readOnly = true)
	public List<Dish> findAll(final SearchByDateCriteria criteria) {
		List<Dish> models = this.findAll(generateSearchByDateQueryPredicate(criteria));
		return models;
	}

	@Transactional(readOnly = true)
	public List<Dish> findAll(SearchByLineCriteria criteria) {
		List<Dish> models = this.findAll(generateSearchByLineQueryPredicate(criteria));
		return models;
	}

	@Transactional(readOnly = true)
	public List<Dish> findAll(final BasicSearchCriteria criteria) {
		List<Dish> models = this.findAll(generateBasicSearchQueryPredicate(criteria));
		return models;
	}

	@Transactional(readOnly = true)
	public Page<Dish> query(final BasicSearchCriteria criteria, PageRequest pageRequest) {
		Page<Dish> models = this.findAll(generateBasicSearchQueryPredicate(criteria), pageRequest);
		return models;
	}

	@Transactional(readOnly = true)
	public List<Dish> findAll(final AdvancedSearchCriteria criteria) {
		List<Dish> meals = this.findAll(generateAdvancedSearchQueryPredicate(criteria));
		return meals;
	}

	@Transactional(readOnly = true)
	public Page<Dish> query(final AdvancedSearchCriteria criteria, PageRequest pageRequest) {
		Page<Dish> meals = this.findAll(generateAdvancedSearchQueryPredicate(criteria), pageRequest);
		return meals;
	}

	/*
	 * Private & Protected Methods
	 */

	private Specification<Dish> generateSearchByDateAndLineQueryPredicate(final SearchByDateCriteria searchByDateCriteria,
			final SearchByLineCriteria searchByLineCriteria) {
		return new Specification<Dish>() {
			@Override
			public Predicate toPredicate(Root<Dish> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				fetchWithDetails(root, query);
				return cb.and(cb.equal(root.get(Dish_.offerDate), searchByDateCriteria.getOfferDate()), cb.equal(root.get(Dish_.line),
						searchByLineCriteria.getLine()));
			}
		};
	}

	private Specification<Dish> generateSearchByDateQueryPredicate(final SearchByDateCriteria criteria) {
		return new Specification<Dish>() {
			@Override
			public Predicate toPredicate(Root<Dish> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				fetchWithDetails(root, query);
				return cb.equal(root.get(Dish_.offerDate), criteria.getOfferDate());
			}
		};
	}

	private Specification<Dish> generateSearchByLineQueryPredicate(final SearchByLineCriteria criteria) {
		return new Specification<Dish>() {
			@Override
			public Predicate toPredicate(Root<Dish> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				fetchWithDetails(root, query);
				return cb.equal(root.get(Dish_.line), criteria.getLine());
			}
		};
	}

	private Specification<Dish> generateBasicSearchQueryPredicate(final BasicSearchCriteria criteria) {
		return new Specification<Dish>() {
			@Override
			public Predicate toPredicate(Root<Dish> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				fetchWithDetails(root, query);
				ArrayList<Predicate> predicates = new ArrayList<Predicate>(4);
				if (!StringUtils.isEmpty(criteria.getKeyword())) {
					predicates.add(cb.like(root.get(Dish_.chineseName), "%" + criteria.getKeyword() + "%"));
					predicates.add(cb.like(root.get(Dish_.englishName), "%" + criteria.getKeyword() + "%"));
					predicates.add(cb.like(root.get(Dish_.description), "%" + criteria.getKeyword() + "%"));
					predicates.add(cb.like(root.get(Dish_.line), "%" + criteria.getKeyword() + "%"));
				}
				return predicates.isEmpty() ? null : predicates.size() == 1 ? predicates.get(0) : cb.or(predicates
						.toArray(new Predicate[predicates.size()]));
			}
		};
	}

	private Specification<Dish> generateAdvancedSearchQueryPredicate(final AdvancedSearchCriteria criteria) {
		return new Specification<Dish>() {
			@Override
			public Predicate toPredicate(Root<Dish> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				fetchWithDetails(root, query);
				ArrayList<Predicate> predicates = new ArrayList<Predicate>(5);
				if (!StringUtils.isEmpty(criteria.getName())) {
					predicates.add(cb.or(cb.like(cb.lower(root.get(Dish_.chineseName)), "%" + criteria.getName().toLowerCase() + "%"), cb
							.like(cb.lower(root.get(Dish_.englishName)), "%" + criteria.getName().toLowerCase() + "%")));
				}
				if (!StringUtils.isEmpty(criteria.getLine())) {
					predicates.add(cb.like(root.get(Dish_.line), "%" + criteria.getLine() + "%"));
				}
				if (!StringUtils.isEmpty(criteria.getDescription())) {
					predicates.add(cb.like(root.get(Dish_.description), "%" + criteria.getDescription() + "%"));
				}
				if (criteria.getStartDate() != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get(Dish_.offerDate), criteria.getStartDate()));
				}
				if (criteria.getEndDate() != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get(Dish_.offerDate), criteria.getEndDate()));
				}
				if (criteria.isRecommended()) {
					predicates.add(cb.equal(root.get(Dish_.isRecommended), true));
				}
				return predicates.isEmpty() ? null : predicates.size() == 1 ? predicates.get(0) : cb.and(predicates
						.toArray(new Predicate[predicates.size()]));
			}
		};
	}

	private void fetchWithDetails(Root<Dish> root, CriteriaQuery<?> query) {
		// root.fetch(Dish_.rateGroup);
		// root.fetch(Dish_.standardPhoto);
		// root.fetch(Dish_.fullScreenPhoto);
	}

}

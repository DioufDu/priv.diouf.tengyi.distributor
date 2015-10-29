package priv.diouf.tengyi.distributor.persistence.repositories.product;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.Modification;
import priv.diouf.tengyi.distributor.persistence.models.Modification_;
import priv.diouf.tengyi.distributor.persistence.models.account.Account_;
import priv.diouf.tengyi.distributor.persistence.models.photo.AvatarPhotoGroup_;
import priv.diouf.tengyi.distributor.persistence.models.photo.ProductPhotoGroup;
import priv.diouf.tengyi.distributor.persistence.models.product.Product;
import priv.diouf.tengyi.distributor.persistence.models.product.Product_;
import priv.diouf.tengyi.distributor.persistence.repositories.FuzzyPredicatesPromise;
import priv.diouf.tengyi.distributor.persistence.repositories.GeneralJpaRepository;
import priv.diouf.tengyi.distributor.services.criterias.product.ProductAdvancedSearchCriteria;
import priv.diouf.tengyi.distributor.services.criterias.product.ProductBasicSearchCriteria;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Transactional
public class ProductRepository extends GeneralJpaRepository<Product, Product_, Long> {

	/*
	 * Constructors
	 */

	@Autowired
	public ProductRepository(EntityManager entityManager) {
		super(Product.class, entityManager);
	}

	/*
	 * Actions
	 */

	@Transactional(readOnly = true)
	public Map<String, Long> generateStatistics() {
		// Parameters
		CriteriaBuilder cb = this.sharedEntityManager.getCriteriaBuilder();
		// Query for product count
		CriteriaQuery<Tuple> queryProductCount = cb.createTupleQuery();
		Root<Product> rootProductCount = queryProductCount.from(Product.class);
		queryProductCount
				// Group by "Product Serie"
				.groupBy(rootProductCount.get(Product_.serie))
				// Select
				.select(cb.tuple(rootProductCount.get(Product_.serie), cb.count(rootProductCount)));
		List<Tuple> queryProductCountResult = this.sharedEntityManager.createQuery(queryProductCount).getResultList();
		// Result
		TreeMap<String, Long> futureProductStatistics = new TreeMap<String, Long>();
		for (Tuple tuple : queryProductCountResult) {
			futureProductStatistics.put((String) tuple.get(0), (Long) tuple.get(1));
		}
		return futureProductStatistics;
	}

	@Transactional(readOnly = true)
	public Product findOneWithAllDetails(final long id) {
		return super.findOne(new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				fetchInfoNode(root);
				return cb.equal(root.get(Product_.id), id);
			}
		});
	}

	@Transactional(readOnly = true)
	public Page<Product> findAll(final ProductBasicSearchCriteria criteria, final PageRequest pageRequest) {
		return super.findAll(new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				fetchInfoNode(root);
				// TODO
				if (criteria == null || StringUtils.isBlank(criteria.getKeyword())) {
					return null;
				}
				return cb.or(generateFuzzyPredicates(cb,
						// keywords
						criteria.getKeyword(),
						// Fields - Scalar Fields
						root.get(Product_.name), root.get(Product_.serie), root.get(Product_.specification), root.get(Product_.model), root
								.get(Product_.comment),
						// Fields - Modification
						root.get(Product_.modification).get(Modification_.createBy).get(Account_.name), root.get(Product_.modification).get(
								Modification_.updateBy).get(Account_.name)));
			}
		}, pageRequest);
	}

	@Transactional(readOnly = true)
	public Page<Product> findAll(final ProductAdvancedSearchCriteria criteria, final PageRequest pageRequest) {
		return super.findAll(new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				fetchInfoNode(root);
				return cb.or(generateFuzzyPredicates(
						// Name
						new FuzzyPredicatesPromise() {
					@Override
					public boolean isValid() {
						return !StringUtils.isBlank(criteria.getName());
					}

					@Override
					public Predicate generate() {
						return cb.like(root.get(Product_.name), String.format("%%s%", criteria.getName()));
					}
				},
						// Login Id
						new FuzzyPredicatesPromise() {
					@Override
					public boolean isValid() {
						return !StringUtils.isBlank(criteria.getSerie());
					}

					@Override
					public Predicate generate() {
						return cb.like(root.get(Product_.serie), String.format("%%s%", criteria.getSerie()));
					}
				},
						// Title
						new FuzzyPredicatesPromise() {
					@Override
					public boolean isValid() {
						return !StringUtils.isBlank(criteria.getName());
					}

					@Override
					public Predicate generate() {
						return cb.like(root.get(Product_.specification), String.format("%%s%", criteria.getSpecification()));
					}
				},
						// Type
						new FuzzyPredicatesPromise() {
					@Override
					public boolean isValid() {
						return !StringUtils.isBlank(criteria.getName());
					}

					@Override
					public Predicate generate() {
						return cb.like(root.get(Product_.model), String.format("%%s%", criteria.getModel()));
					}
				},
						// Status
						new FuzzyPredicatesPromise() {
					@Override
					public boolean isValid() {
						return !StringUtils.isBlank(criteria.getName());
					}

					@Override
					public Predicate generate() {
						return cb.equal(root.get(Product_.status), criteria.getStatus());
					}
				},
						// Modification - Create By - Name
						new FuzzyPredicatesPromise() {
					@Override
					public boolean isValid() {
						return !StringUtils.isBlank(criteria.getName());
					}

					@Override
					public Predicate generate() {
						return cb.like(root.get(Product_.modification).get(Modification_.createBy).get(Account_.name), String.format("%%s%",
								criteria.getCreateBy()));
					}
				},
						// Modification - Update By - Name
						new FuzzyPredicatesPromise() {
					@Override
					public boolean isValid() {
						return !StringUtils.isBlank(criteria.getName());
					}

					@Override
					public Predicate generate() {
						return cb.like(root.get(Product_.modification).get(Modification_.updateBy).get(Account_.name), String.format("%%s%",
								criteria.getUpdateBy()));
					}
				}));
			}
		}, pageRequest);
	}

	/*
	 * Private & Protected Methods
	 */

	private void fetchInfoNode(Root<Product> root) {
		root.fetch(Product_.pricingModel);
		Fetch<Product, ProductPhotoGroup> fetchPhoto = root.fetch(Product_.productPhotoGroup);
		fetchPhoto.fetch(AvatarPhotoGroup_.thumbnailPhoto);
		// fetchAvatar.fetch(AvatarPhotoGroup_.standardPhoto);
		fetchPhoto.fetch(AvatarPhotoGroup_.fullScreenPhoto);
		// fetchAvatar.fetch(AvatarPhotoGroup_.originalPhoto);
		Fetch<Product, Modification> fetchModification = root.fetch(Product_.modification);
		fetchModification.fetch(Modification_.createBy);
		fetchModification.fetch(Modification_.updateBy);
	}

}

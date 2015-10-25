package priv.diouf.tengyi.distributor.services;

import java.util.Calendar;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.Dish;
import priv.diouf.tengyi.distributor.persistence.repositories.DishRepository;
import priv.diouf.tengyi.distributor.services.exceptions.SpecifiedEntityNotFoundException;
import priv.diouf.tengyi.distributor.services.models.AdvancedSearchCriteria;
import priv.diouf.tengyi.distributor.services.models.BasicSearchCriteria;
import priv.diouf.tengyi.distributor.services.models.SearchByDateCriteria;
import priv.diouf.tengyi.distributor.services.models.SearchByLineCriteria;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class DishQueryService {

	/*
	 * Fields
	 */

	private static final String DEFAULT_RECOMMENDED_DISHES_SORT_PROPERTIES = "offerDate";
	// Dish_.offerDate.getName();

	private static final String DEFAULT_RECENT_DISHES_SORT_PROPERTIES = "offerDate";
	// Dish_.offerDate.getName();

	private static final String DEFAULT_LASTEST_DISHES_SORT_PROPERTIES = "modification.lastModifiedDateTime";
	// String.format("%s.%s", Dish_.modification.getName(), Modification_.lastModifiedDateTime.getName());

	protected static final String DEFAULT_POPULAR_DISHES_SORT_PROPERTIES = "rateGroup.count";
	// String.format("%s.%s", Dish_.rateGroup.getName(), RateGroup_.count.getName());

	@Autowired
	protected DishRepository dishRepository;

	/*
	 * Queries
	 */

	@Transactional(readOnly = true)
	public Page<Dish> findAll(AdvancedSearchCriteria criteria, PageRequest pageRequest) {
		return dishRepository.query(criteria, pageRequest);
	}

	@Transactional(readOnly = true)
	public Page<Dish> findAll(BasicSearchCriteria criteria, PageRequest pageRequest) {
		return dishRepository.query(criteria, pageRequest);
	}

	@Transactional(readOnly = true)
	public List<Dish> findAllByOfferDateAndLine(Calendar offerDate, String line) {
		SearchByDateCriteria searchByDateCriteria = new SearchByDateCriteria();
		searchByDateCriteria.setOfferDate(offerDate);
		SearchByLineCriteria searchByLineCriteria = new SearchByLineCriteria();
		searchByLineCriteria.setLine(line);
		return dishRepository.findAll(searchByDateCriteria, searchByLineCriteria);
	}

	@Transactional(readOnly = true)
	public List<Dish> findAllByOfferDate(Calendar offerDate) {
		SearchByDateCriteria criteria = new SearchByDateCriteria();
		criteria.setOfferDate(offerDate);
		return dishRepository.findAll(criteria);
	}

	@Transactional(readOnly = true)
	public List<Dish> findAllByLine(String line) {
		SearchByLineCriteria criteria = new SearchByLineCriteria();
		criteria.setLine(line);
		return dishRepository.findAll(criteria);
	}

	@Transactional(readOnly = true)
	public Page<Dish> findAll(Pageable pageable) {
		return dishRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Page<Dish> findAllRecommended(int pageIndex, int pageSize) {
		return dishRepository.findAllRecommended(new PageRequest(pageIndex, pageSize, Direction.ASC,
				DEFAULT_RECOMMENDED_DISHES_SORT_PROPERTIES));
	}

	@Transactional(readOnly = true)
	public Page<Dish> findAllRecent(int pageIndex, int pageSize) {
		return dishRepository.findAllRecent(new PageRequest(pageIndex, pageSize, Direction.DESC, DEFAULT_RECENT_DISHES_SORT_PROPERTIES));
	}

	@Transactional(readOnly = true)
	public Page<Dish> findAllLatest(int pageIndex, int pageSize) {
		return dishRepository.findAll(new PageRequest(pageIndex, pageSize, Direction.DESC, DEFAULT_LASTEST_DISHES_SORT_PROPERTIES));
	}

	@Transactional(readOnly = true)
	public Page<Dish> findAllPopular(int pageIndex, int pageSize) {
		return dishRepository.findAllPopular(new PageRequest(pageIndex, pageSize, Direction.DESC, DEFAULT_POPULAR_DISHES_SORT_PROPERTIES));
	}

	@Transactional(readOnly = true)
	public Dish findOneWithDetails(Long id) {
		if (id == null || id < 1) {
			throw new SpecifiedEntityNotFoundException("dish");
		}
		Dish existedDish = dishRepository.findOne(id);
		if (null == existedDish) {
			throw new SpecifiedEntityNotFoundException("dish");
		}
		return existedDish;
	}

	@Transactional(readOnly = true)
	public SortedMap<Calendar, long[]> generateStatistics() {
		TreeMap<Calendar, long[]> dishStatistics = new TreeMap<Calendar, long[]>();
		for (Entry<Calendar, long[]> statistics : dishRepository.generateStatistics().entrySet()) {
			dishStatistics.put(statistics.getKey(), statistics.getValue());
		}
		return dishStatistics;
	}

	/*
	 * Private & Protected Methods
	 */
}

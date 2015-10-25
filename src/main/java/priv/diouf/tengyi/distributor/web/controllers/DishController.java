package priv.diouf.tengyi.distributor.web.controllers;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import priv.diouf.tengyi.distributor.persistence.models.Dish;
import priv.diouf.tengyi.distributor.persistence.models.RateGroup;
import priv.diouf.tengyi.distributor.services.DishMaintanceService;
import priv.diouf.tengyi.distributor.services.DishQueryService;
import priv.diouf.tengyi.distributor.services.PhotoQueryService;
import priv.diouf.tengyi.distributor.services.RateService;
import priv.diouf.tengyi.distributor.web.annontations.AuthenticatedRole;
import priv.diouf.tengyi.distributor.web.models.requests.dish.AdvancedSearchRequest;
import priv.diouf.tengyi.distributor.web.models.requests.dish.BasicSearchRequest;
import priv.diouf.tengyi.distributor.web.models.requests.dish.CreationRequest;
import priv.diouf.tengyi.distributor.web.models.requests.dish.RateRequest;
import priv.diouf.tengyi.distributor.web.models.requests.dish.UpdateRequest;
import priv.diouf.tengyi.distributor.web.models.responses.dish.DishDateGroup;
import priv.diouf.tengyi.distributor.web.models.responses.dish.DishDetail;
import priv.diouf.tengyi.distributor.web.models.responses.dish.DishLineGroup;
import priv.diouf.tengyi.distributor.web.models.responses.dish.DishPage;
import priv.diouf.tengyi.distributor.web.models.responses.dish.DishStatistics;
import priv.diouf.tengyi.distributor.web.models.responses.rate.RateGroupInfo;

@RestController
public class DishController {

	/*
	 * Fields
	 */

	private static final int DEFAULT_LATEST_DISH_COUNT = 30;

	private static final int DEFAULT_POPULAR_DISH_COUNT = 30;

	private static final int DEFAULT_RECOMMENDED_DISH_COUNT = 4;

	private static final int DEFAULT_RECENT_DISH_COUNT = 5;

	@Autowired
	protected DishQueryService dishQueryService;

	@Autowired
	protected DishMaintanceService dishMaintanceService;

	@Autowired
	protected PhotoQueryService photoQueryService;

	@Autowired
	protected RateService rateService;

	@Autowired
	protected HttpServletResponse response;

	/*
	 * Retrieve Actions
	 */

	@RequestMapping(value = "dish", method = RequestMethod.GET)
	public DishStatistics generateStatistics() {
		return new DishStatistics(dishQueryService.generateStatistics());
	}

	@RequestMapping(value = "dish/{dishId}", method = RequestMethod.GET)
	public DishDetail findOneWithDetails(@PathVariable("dishId") long dishId) {
		return new DishDetail(dishQueryService.findOneWithDetails(dishId));
	}

	/*
	 * Retrieve Actions - Group
	 */

	@RequestMapping(value = "dish/group/date", method = RequestMethod.GET)
	public DishLineGroup findAllByDate() {
		List<Dish> dishes = dishQueryService.findAllByOfferDate(Calendar.getInstance());
		return new DishLineGroup(dishes);
	}

	@RequestMapping(value = "dish/group/date/{offerDate}/line/{line}", method = RequestMethod.GET)
	public DishLineGroup findAllByDateAndLine(@PathVariable("offerDate") long offerDateInMillis, @PathVariable("line") String line) {
		Calendar offerDate = Calendar.getInstance();
		offerDate.setTimeInMillis(offerDateInMillis);
		List<Dish> dishes = dishQueryService.findAllByOfferDateAndLine(offerDate, line);
		return new DishLineGroup(dishes);
	}

	@RequestMapping(value = "dish/group/date/{offerDate}", method = RequestMethod.GET)
	public DishLineGroup findAllByDate(@PathVariable("offerDate") long offerDateInMillis) {
		Calendar offerDate = Calendar.getInstance();
		offerDate.setTimeInMillis(offerDateInMillis);
		List<Dish> dishes = dishQueryService.findAllByOfferDate(offerDate);
		return new DishLineGroup(dishes);
	}

	@RequestMapping(value = "dish/group/line/{line}", method = RequestMethod.GET)
	public DishDateGroup findAllByLine(@PathVariable("line") String line) {
		List<Dish> dishes = dishQueryService.findAllByLine(line);
		return new DishDateGroup(dishes);
	}

	/*
	 * Retrieve Actions - Search
	 */

	@AuthenticatedRole("Admin")
	@RequestMapping(value = "dish/search/basic", method = RequestMethod.POST)
	public DishPage basicSearch(@RequestBody BasicSearchRequest queryRequest) {
		return generateDishPageResponse(dishQueryService.findAll(queryRequest.getCriteria(), queryRequest.getPageRequest()));
	}

	@AuthenticatedRole("Admin")
	@RequestMapping(value = "dish/search/advanced", method = RequestMethod.POST)
	public DishPage advancedSearch(@RequestBody AdvancedSearchRequest queryRequest) {
		return generateDishPageResponse(dishQueryService.findAll(queryRequest.getCriteria(), queryRequest.getPageRequest()));
	}

	/*
	 * Retrieve Actions - Page by scenario
	 */

	@RequestMapping(value = "dish/recommended", method = RequestMethod.GET)
	public DishPage findAllRecommended() {
		return this.findAllRecommended(DEFAULT_RECOMMENDED_DISH_COUNT);
	}

	@RequestMapping(value = "dish/recommended/{count}", method = RequestMethod.GET)
	public DishPage findAllRecommended(@PathVariable("count") int count) {
		return this.findAllRecommended(0, DEFAULT_RECOMMENDED_DISH_COUNT);
	}

	@RequestMapping(value = "dish/recommended/{pageSize}/{pageIndex}", method = RequestMethod.GET)
	public DishPage findAllRecommended(@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
		return generateDishPageResponse(dishQueryService.findAllRecommended(pageIndex, pageSize));
	}

	@RequestMapping(value = "dish/recent", method = RequestMethod.GET)
	public DishPage findRecentDishes() {
		return this.findRecentDishes(DEFAULT_RECENT_DISH_COUNT);
	}

	@RequestMapping(value = "dish/recent/{count}", method = RequestMethod.GET)
	public DishPage findRecentDishes(@PathVariable("count") int count) {
		return this.findRecentDishes(0, DEFAULT_RECENT_DISH_COUNT);
	}

	@RequestMapping(value = "dish/recent/{pageSize}/{pageIndex}", method = RequestMethod.GET)
	public DishPage findRecentDishes(@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
		return generateDishPageResponse(dishQueryService.findAllRecent(pageIndex, pageSize));
	}

	@RequestMapping(value = "dish/latest", method = RequestMethod.GET)
	public DishPage findLatestDishes() {
		return this.findLatestDishes(DEFAULT_LATEST_DISH_COUNT);
	}

	@RequestMapping(value = "dish/latest/{count}", method = RequestMethod.GET)
	public DishPage findLatestDishes(@PathVariable("count") int count) {
		return this.findLatestDishes(0, DEFAULT_LATEST_DISH_COUNT);
	}

	@RequestMapping(value = "dish/latest/{pageSize}/{pageIndex}", method = RequestMethod.GET)
	public DishPage findLatestDishes(@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
		return generateDishPageResponse(dishQueryService.findAllLatest(pageIndex, pageSize));
	}

	@RequestMapping(value = "dish/popular", method = RequestMethod.GET)
	public DishPage findPopularDishes() {
		return this.findPopularDishes(DEFAULT_POPULAR_DISH_COUNT);
	}

	@RequestMapping(value = "dish/popular/{count}", method = RequestMethod.GET)
	public DishPage findPopularDishes(@PathVariable("count") int count) {
		return this.findPopularDishes(0, count);
	}

	@RequestMapping(value = "dish/popular/{pageSize}/{pageIndex}", method = RequestMethod.GET)
	public DishPage findPopularDishes(@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
		return generateDishPageResponse(dishQueryService.findAllPopular(pageIndex, pageSize));
	}

	/*
	 * Retrieve Photo
	 */

	/*
	 * Create
	 */

	@AuthenticatedRole("Admin")
	@RequestMapping(value = "dish", method = RequestMethod.POST)
	public DishDetail createDish(@RequestBody(required = true) CreationRequest request) {
		Dish dish = dishMaintanceService.create(request);
		return new DishDetail(dish);
	}

	/*
	 * Update
	 */

	@AuthenticatedRole("Admin")
	@RequestMapping(value = "dish/{dishId}", method = RequestMethod.PUT)
	public DishDetail modifyDish(@RequestBody(required = true) UpdateRequest request, @PathVariable("dishId") long dishId) {
		Dish dish = dishMaintanceService.update(dishId, request);
		return new DishDetail(dish);
	}

	/*
	 * Delete
	 */

	@AuthenticatedRole("Admin")
	@RequestMapping(value = "dish/{dishId}", method = RequestMethod.DELETE)
	public void deleteDish(@PathVariable("dishId") long dishId) {
		dishMaintanceService.delete(dishId);
	}

	/*
	 * Others
	 */

	@RequestMapping(value = "dish/{dishId}/rate", method = RequestMethod.POST)
	public RateGroupInfo rateDish(@RequestBody(required = true) RateRequest request, @PathVariable("dishId") long dishId) {
		RateGroup rateGroup = rateService.rateByDishId(request, dishId);
		return new RateGroupInfo(rateGroup);
	}

	/*
	 * Protected & Private Methods
	 */

	protected DishPage generateDishPageResponse(Page<Dish> dishes) {
		return new DishPage(DishPage.createDishDetails(dishes.getContent()), new PageRequest(dishes.getNumber(), dishes.getSize()), dishes
				.getTotalElements());
	}
}

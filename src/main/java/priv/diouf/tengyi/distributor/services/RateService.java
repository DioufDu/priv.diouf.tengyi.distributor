package priv.diouf.tengyi.distributor.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import priv.diouf.tengyi.distributor.persistence.models.Rate;
import priv.diouf.tengyi.distributor.persistence.models.RateGroup;
import priv.diouf.tengyi.distributor.persistence.repositories.RateGroupRepository;
import priv.diouf.tengyi.distributor.services.exceptions.SpecifiedEntityNotFoundException;
import priv.diouf.tengyi.distributor.web.models.requests.dish.RateRequest;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class RateService {

	/*
	 * Fields
	 */

	@Autowired
	protected RateGroupRepository rateGroupRepository;

	/*
	 * Queries
	 */

	@Transactional
	public RateGroup rateByDishId(RateRequest request, Long dishId) {
		// Validations
		if (0L == dishId) {
			throw new SpecifiedEntityNotFoundException("dish");
		}
		RateGroup rateGroup = rateGroupRepository.findOneByMealId(dishId);
		if (null == rateGroup) {
			throw new SpecifiedEntityNotFoundException("dish");
		}
		// Create New Rate
		Rate rate = new Rate();
		rate.setTaste(request.getTaste());
		rate.setService(request.getService());
		rate.setStyle(request.getStyle());
		if (CollectionUtils.isEmpty(rateGroup.getRates())) {
			rateGroup.setRates(new HashSet<Rate>(1));
		}
		rateGroup.getRates().add(rate);
		// Adjust Rate Group Information
		BigDecimal ratedCount = new BigDecimal(rateGroup.getCount());
		BigDecimal ratingCount = ratedCount.add(new BigDecimal(1));
		rateGroup.setTaste((rateGroup.getTaste().multiply(ratedCount).add(new BigDecimal(request.getTaste()))).divide(
				ratingCount, RoundingMode.HALF_EVEN));
		rateGroup.setService((rateGroup.getService().multiply(ratedCount).add(new BigDecimal(request.getService())))
				.divide(ratingCount, RoundingMode.HALF_EVEN));
		rateGroup.setStyle((rateGroup.getStyle().multiply(ratedCount).add(new BigDecimal(request.getStyle()))).divide(
				ratingCount, RoundingMode.HALF_EVEN));
		rateGroup.setCount(ratingCount.longValue());
		// Save
		return rateGroup;
	}

	/*
	 * Private & Protected Methods
	 */
}

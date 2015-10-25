package priv.diouf.tengyi.distributor.persistence.initializer.factories;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.Dish;
import priv.diouf.tengyi.distributor.persistence.models.Rate;
import priv.diouf.tengyi.distributor.persistence.models.RateGroup;
import priv.diouf.tengyi.distributor.persistence.repositories.DishRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.RateGroupRepository;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RateGroupEntityFactory extends GeneralEntityFactory<RateGroup> implements TransactionalDataEntityFactory<RateGroup> {

	/*
	 * Fields
	 */

	@Autowired
	protected RateGroupRepository rateGroupRepository;

	@Autowired
	protected DishRepository dishRepository;

	@Autowired
	protected DishEntityFactory dishEntityFactory;

	/*
	 * Creations
	 */

	@Override
	@Transactional
	protected void fulfill(List<RateGroup> allCreatedEntities) {
		for (Dish dish : dishEntityFactory.getAll()) {
			RateGroup rateGroup = new RateGroup(dish);
			dish.setRateGroup(rateGroup);
			// - Rates
			long offerDateInMillis = dish.getOfferDate().getTimeInMillis();
			int rateCount = RandomUtils.nextInt(1, 30);
			rateGroup.setRates(new HashSet<Rate>(rateCount));
			for (int i = 0; i < rateCount; i++) {
				Rate rate = new Rate();
				Calendar rateDate = Calendar.getInstance();
				rateDate.set(Calendar.MILLISECOND, -(int) RandomUtils.nextLong(0, Math.abs(rateDate.getTimeInMillis() - offerDateInMillis)));
				rate.setDateTime(rateDate);
				rate.setTaste((int) (6 - RandomUtils.nextDouble(0, 2) * RandomUtils.nextDouble(0, 2.5)));
				rate.setService((int) (6 - RandomUtils.nextDouble(0, 2) * RandomUtils.nextDouble(0, 2.5)));
				rate.setStyle((int) (6 - RandomUtils.nextDouble(0, 2) * RandomUtils.nextDouble(0, 2.5)));
				rateGroup.getRates().add(rate);
			}
			// - Rate Group - Statistics
			int taste = 0;
			int service = 0;
			int style = 0;
			for (Rate rate : rateGroup.getRates()) {
				taste += rate.getTaste();
				service += rate.getService();
				style += rate.getStyle();
			}
			BigDecimal rateCountDecimal = new BigDecimal(rateGroup.getRates().size());
			rateGroup.setTaste(new BigDecimal(taste).divide(rateCountDecimal, 20, RoundingMode.HALF_EVEN));
			rateGroup.setService(new BigDecimal(service).divide(rateCountDecimal, 20, RoundingMode.HALF_EVEN));
			rateGroup.setStyle(new BigDecimal(style).divide(rateCountDecimal, 20, RoundingMode.HALF_EVEN));
			rateGroup.setCount(rateCountDecimal.longValue());
			// Save
			allCreatedEntities.add(rateGroup);
		}
		dishRepository.saveAndFlush(dishEntityFactory.getAll());
	}

	/*
	 * Metadata Information
	 */

	@Override
	protected EntityFactory<?>[] getDependEntityFactories() {
		return new EntityFactory<?>[] { dishEntityFactory };
	}

	@Override
	public Class<RateGroup> getEntityType() {
		return RateGroup.class;
	}

	@Override
	protected JpaRepository<RateGroup, Long> getGeneralJpaRepository() {
		return rateGroupRepository;
	}
}

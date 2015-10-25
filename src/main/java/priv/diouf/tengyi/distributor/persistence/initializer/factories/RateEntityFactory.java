package priv.diouf.tengyi.distributor.persistence.initializer.factories;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.Dish;
import priv.diouf.tengyi.distributor.persistence.models.Rate;
import priv.diouf.tengyi.distributor.persistence.models.RateGroup;
import priv.diouf.tengyi.distributor.persistence.repositories.RateGroupRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.RateRepository;

//@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RateEntityFactory extends GeneralEntityFactory<Rate> implements TransactionalDataEntityFactory<Rate> {

	/*
	 * Fields
	 */

	@Autowired
	protected RateRepository rateRepository;

	@Autowired
	protected RateGroupRepository rateGroupRepository;

	@Autowired
	protected DishEntityFactory dishEntityFactory;

	@Autowired
	protected RateGroupEntityFactory rateGroupEntityFactory;

	/*
	 * Creations
	 */

	@Override
	@Transactional
	protected void fulfill(List<Rate> allCreatedEntities) {
		for (Dish dish : dishEntityFactory.getAll()) {
			RateGroup rateGroup = dish.getRateGroup();
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
				allCreatedEntities.add(rate);
			}
		}
	}

	/*
	 * Metadata Information
	 */

	@Override
	protected EntityFactory<?>[] getDependEntityFactories() {
		return new EntityFactory<?>[] { rateGroupEntityFactory };
	}

	@Override
	public Class<Rate> getEntityType() {
		return Rate.class;
	}

	@Override
	protected JpaRepository<Rate, Long> getGeneralJpaRepository() {
		return rateRepository;
	}

}

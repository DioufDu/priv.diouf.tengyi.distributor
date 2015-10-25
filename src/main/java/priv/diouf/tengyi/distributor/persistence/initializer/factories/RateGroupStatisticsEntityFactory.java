package priv.diouf.tengyi.distributor.persistence.initializer.factories;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.Rate;
import priv.diouf.tengyi.distributor.persistence.models.RateGroup;
import priv.diouf.tengyi.distributor.persistence.repositories.DishRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.RateGroupRepository;

//@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RateGroupStatisticsEntityFactory extends GeneralEntityFactory<RateGroup> implements TransactionalDataEntityFactory<RateGroup> {

	/*
	 * Fields
	 */

	@Autowired
	protected RateGroupRepository rateGroupRepository;

	@Autowired
	protected DishRepository dishRepository;

	@Autowired
	protected RateGroupEntityFactory rateGroupEntityFactory;

	@Autowired
	protected RateEntityFactory rateEntityFactory;

	/*
	 * Creations
	 */

	@Override
	@Transactional
	protected void fulfill(List<RateGroup> allCreatedEntities) {
		for (RateGroup rateGroup : rateGroupEntityFactory.getAll()) {
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
			allCreatedEntities.add(rateGroup);
		}
	}

	/*
	 * Metadata Information
	 */

	@Override
	protected EntityFactory<?>[] getDependEntityFactories() {
		return new EntityFactory<?>[] { rateEntityFactory };
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

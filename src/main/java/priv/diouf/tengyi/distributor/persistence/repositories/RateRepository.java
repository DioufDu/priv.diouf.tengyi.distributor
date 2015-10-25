package priv.diouf.tengyi.distributor.persistence.repositories;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.Rate;
import priv.diouf.tengyi.distributor.persistence.models.Rate_;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class RateRepository extends GeneralJpaRepository<Rate, Rate_, Long> {

	/*
	 * Constructors
	 */

	@Autowired
	public RateRepository(EntityManager entityManager) {
		super(Rate.class, entityManager);
	}
}

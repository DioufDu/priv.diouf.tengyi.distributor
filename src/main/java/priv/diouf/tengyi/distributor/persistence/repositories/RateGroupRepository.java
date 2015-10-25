package priv.diouf.tengyi.distributor.persistence.repositories;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.Dish_;
import priv.diouf.tengyi.distributor.persistence.models.RateGroup;
import priv.diouf.tengyi.distributor.persistence.models.RateGroup_;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class RateGroupRepository extends GeneralJpaRepository<RateGroup, RateGroup_, Long> {

	/*
	 * Constructors
	 */

	@Autowired
	public RateGroupRepository(EntityManager entityManager) {
		super(RateGroup.class, entityManager);
	}

	/*
	 * Actions
	 */

	@Transactional(readOnly = true)
	public RateGroup findOneByMealId(final Long mealId) {
		return this.findOne(new Specification<RateGroup>() {
			@Override
			public Predicate toPredicate(Root<RateGroup> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get(RateGroup_.dish).get(Dish_.id), mealId);
			}
		});
	}
}

package priv.diouf.tengyi.distributor.persistence.repositories.product;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.product.Product;
import priv.diouf.tengyi.distributor.persistence.models.product.Product_;
import priv.diouf.tengyi.distributor.persistence.repositories.GeneralJpaRepository;

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

}

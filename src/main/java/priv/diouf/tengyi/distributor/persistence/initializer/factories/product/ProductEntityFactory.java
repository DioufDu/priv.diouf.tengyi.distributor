package priv.diouf.tengyi.distributor.persistence.initializer.factories.product;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.initializer.factories.EntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.GeneralEntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.TransactionalDataEntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.account.AccountEntityFactory;
import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.persistence.models.product.Product;
import priv.diouf.tengyi.distributor.persistence.repositories.product.ProductRepository;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductEntityFactory extends GeneralEntityFactory<Product> implements TransactionalDataEntityFactory<Product> {

	/*
	 * Fields
	 */

	@Autowired
	protected ProductRepository productRepository;

	@Autowired
	protected AccountEntityFactory accountEntityFactory;

	/*
	 * Creations
	 */

	@Override
	@Transactional
	protected void fulfill(List<Product> allCreatedEntities) {
		for (Account account : accountEntityFactory.getAll()) {
			int productCount = RandomUtils.nextInt(0, 30);
			for (int i = 0; i < productCount; i++) {
				Product product = new Product();
				// TODO: Scalar Properties
				allCreatedEntities.add(product);
			}
		}
	}

	/*
	 * Metadata Information
	 */

	@Override
	protected EntityFactory<?>[] getDependEntityFactories() {
		return new EntityFactory<?>[] { accountEntityFactory };
	}

	@Override
	public Class<Product> getEntityType() {
		return Product.class;
	}

	@Override
	protected JpaRepository<Product, Long> getGeneralJpaRepository() {
		return productRepository;
	}

}

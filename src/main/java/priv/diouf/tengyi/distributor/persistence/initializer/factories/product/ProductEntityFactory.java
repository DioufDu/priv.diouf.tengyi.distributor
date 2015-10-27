package priv.diouf.tengyi.distributor.persistence.initializer.factories.product;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.common.models.enums.AccountType;
import priv.diouf.tengyi.distributor.common.models.enums.PriceStrategy;
import priv.diouf.tengyi.distributor.common.models.enums.ProductStatus;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.EntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.GeneralEntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.TransactionalDataEntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.account.AccountEntityFactory;
import priv.diouf.tengyi.distributor.persistence.models.Modification;
import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.persistence.models.product.PricingModel;
import priv.diouf.tengyi.distributor.persistence.models.product.Product;
import priv.diouf.tengyi.distributor.persistence.repositories.product.ProductRepository;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductEntityFactory extends GeneralEntityFactory<Product> implements TransactionalDataEntityFactory<Product> {

	/*
	 * Fields
	 */

	private static final String[] SERIES = new String[] { "Business", "elaxation", "Household", "Fashion" };
	private static final String[] MODELS = new String[] { "Desktop", "Bench", "Floor", "Sofa" };
	private static final String[] COMMENTS = new String[] { "New", "Classic", "Hot", "Sales" };

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
			if (account.getType() != AccountType.ADMIN) {
				continue;
			}
			int productCount = RandomUtils.nextInt(0, 30);
			for (int idx = productCount; idx > 0; idx--) {
				Product product = new Product();
				// TODO: Scalar Properties
				product.setStatus(ProductStatus.values()[RandomUtils.nextInt(0, ProductStatus.values().length)]);
				product.setName("ProductExample" + RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(5, 10)));
				product.setSpecification(String.valueOf(RandomUtils.nextLong(100000000000L, 999999999999L)));
				product.setModel(MODELS[RandomUtils.nextInt(0, MODELS.length)]);
				product.setSerie(SERIES[RandomUtils.nextInt(0, SERIES.length)]);
				product.setComment(COMMENTS[RandomUtils.nextInt(0, COMMENTS.length)]);
				// Navigations - Pricing Model
				product.setPricingModel(new PricingModel(product));
				if (RandomUtils.nextInt(0, 5) != 2) {
					product.getPricingModel().setStrategy(PriceStrategy.AUTO);
				} else {
					product.getPricingModel().setStrategy(PriceStrategy.MANUALLY);
				}
				BigDecimal factoryPrice = new BigDecimal(RandomUtils.nextDouble(500, 10000));
				product.getPricingModel().setFactoryPrice(factoryPrice);
				product.getPricingModel().setUnifiedPrice(factoryPrice.multiply(new BigDecimal(3.5)));
				product.getPricingModel().setStorePrice(factoryPrice.multiply(new BigDecimal(3.5).multiply(new BigDecimal(0.4))));
				product.getPricingModel().setTerminalPice(factoryPrice.multiply(new BigDecimal(3.5).multiply(new BigDecimal(0.6))));
				// Navigations - Modification
				account.setModification(new Modification(account));
				Calendar createOn = Calendar.getInstance();
				createOn.add(Calendar.DATE, -RandomUtils.nextInt(30, 60));
				product.getModification().setCreateOn(createOn);
				Calendar updateOn = Calendar.getInstance();
				updateOn.add(Calendar.DATE, -RandomUtils.nextInt(3, 30));
				product.getModification().setUpdateOn(updateOn);
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

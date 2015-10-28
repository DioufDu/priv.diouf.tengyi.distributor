package priv.diouf.tengyi.distributor.persistence.initializer.factories.product;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.common.models.enums.AccountType;
import priv.diouf.tengyi.distributor.common.models.enums.PriceStrategy;
import priv.diouf.tengyi.distributor.common.models.enums.ProductStatus;
import priv.diouf.tengyi.distributor.persistence.exceptions.CommonPersistenceException;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.EntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.GeneralEntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.TransactionalDataEntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.account.AccountEntityFactory;
import priv.diouf.tengyi.distributor.persistence.models.Modification;
import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.persistence.models.photo.ProductPhotoGroup;
import priv.diouf.tengyi.distributor.persistence.models.product.PricingModel;
import priv.diouf.tengyi.distributor.persistence.models.product.Product;
import priv.diouf.tengyi.distributor.persistence.repositories.product.ProductPhotoGroupRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.product.ProductRepository;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ProductEntityFactory extends GeneralEntityFactory<Product> implements TransactionalDataEntityFactory<Product> {

	/*
	 * Fields
	 */

	private static final String[] SERIES = new String[] { "Business", "Relaxation", "Household", "Fashion" };
	private static final String[] MODELS = new String[] { "Desktop", "Bench", "Floor", "Sofa" };
	private static final String[] COMMENTS = new String[] { "New", "Classic", "Hot", "Sales" };

	@Autowired
	protected ProductRepository productRepository;

	@Autowired
	protected AccountEntityFactory accountEntityFactory;

	@Autowired
	protected ProductPhotoGroupRepository productPhotoGroupRepository;

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
				// Scalar Properties
				product.setStatus(ProductStatus.values()[RandomUtils.nextInt(0, ProductStatus.values().length)]);
				product.setName("ProductExample" + RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(5, 10)));
				product.setSpecification(String.valueOf(RandomUtils.nextLong(100000000000L, 999999999999L)));
				product.setModel(MODELS[RandomUtils.nextInt(0, MODELS.length)]);
				product.setSerie(SERIES[RandomUtils.nextInt(0, SERIES.length)]);
				product.setComment(COMMENTS[RandomUtils.nextInt(0, COMMENTS.length)]);
				// Navigations
				product.setPricingModel(generatePricingModel(product));
				product.setModification(this.generateModification(account));
				product.setProductPhotoGroup(this.generatePhoto(product, account));

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

	/*
	 * Private & Protected Methods
	 */

	private PricingModel generatePricingModel(Product product) {
		PricingModel pricingModel = new PricingModel(product);
		// Navigations - Pricing Model
		if (RandomUtils.nextInt(0, 5) != 2) {
			pricingModel.setStrategy(PriceStrategy.AUTO);
		} else {
			pricingModel.setStrategy(PriceStrategy.MANUALLY);
		}
		BigDecimal factoryPrice = new BigDecimal(RandomUtils.nextDouble(500, 10000));
		pricingModel.setFactoryPrice(factoryPrice);
		pricingModel.setUnifiedPrice(factoryPrice.multiply(new BigDecimal(3.5)));
		pricingModel.setStorePrice(factoryPrice.multiply(new BigDecimal(3.5).multiply(new BigDecimal(0.4))));
		pricingModel.setTerminalPice(factoryPrice.multiply(new BigDecimal(3.5).multiply(new BigDecimal(0.6))));
		// Return
		return pricingModel;
	}

	private Modification generateModification(Account account) {
		Modification modification = new Modification(account);
		// Navigations - Modification
		Calendar createOn = Calendar.getInstance();
		createOn.add(Calendar.DATE, -RandomUtils.nextInt(30, 60));
		modification.setCreateOn(createOn);
		Calendar updateOn = Calendar.getInstance();
		updateOn.add(Calendar.DATE, -RandomUtils.nextInt(3, 30));
		modification.setUpdateOn(updateOn);
		// Return
		return modification;
	}

	private ProductPhotoGroup generatePhoto(Product product, Account account) {
		ProductPhotoGroup productPhotoGroup = new ProductPhotoGroup(product);
		// Navigations - Modification
		productPhotoGroup.setModification(new Modification(product.getModification().getUpdateBy()));
		Calendar createOn = Calendar.getInstance();
		createOn.add(Calendar.DATE, -RandomUtils.nextInt(30, 60));
		productPhotoGroup.getModification().setCreateOn(createOn);
		Calendar updateOn = Calendar.getInstance();
		updateOn.add(Calendar.DATE, -RandomUtils.nextInt(3, 30));
		productPhotoGroup.getModification().setUpdateOn(updateOn);
		String photoName = "photos/" + RandomUtils.nextInt(1, 17) + ".jpg";
		try (InputStream photoStream = this.getClass().getResourceAsStream(photoName)) {
			if (photoStream == null || photoStream.available() < 1) {
				return productPhotoGroup;
			}
			return productPhotoGroupRepository.generatePhotoGroup(productPhotoGroup, IOUtils.toByteArray(photoStream));
		} catch (IOException ex) {
			throw new CommonPersistenceException("Can't store the photo into product.", ex);
		}
	}
}

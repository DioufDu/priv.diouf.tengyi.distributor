package priv.diouf.tengyi.distributor.persistence.initializer.factories.product;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.exceptions.CommonPersistenceException;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.EntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.GeneralEntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.TransactionalDataEntityFactory;
import priv.diouf.tengyi.distributor.persistence.models.photo.ProductPhotoGroup;
import priv.diouf.tengyi.distributor.persistence.models.product.Product;
import priv.diouf.tengyi.distributor.persistence.repositories.product.ProductPhotoGroupRepository;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ProductPhotoGroupEntityFactory extends GeneralEntityFactory<ProductPhotoGroup> implements TransactionalDataEntityFactory<ProductPhotoGroup> {

	/*
	 * Fields
	 */

	@Autowired
	protected ProductPhotoGroupRepository productPhotoGroupRepository;

	@Autowired
	protected ProductEntityFactory productEntityFactory;

	/*
	 * Creations
	 */

	@Override
	@Transactional
	protected void fulfill(List<ProductPhotoGroup> allCreatedEntities) {
		int index = 0;
		for (Product product : productEntityFactory.getAll()) {
			product.setProductPhotoGroups(new ArrayList<ProductPhotoGroup>());
			int photoCount = 1;// RandomUtils.nextInt(1, 12);
			for (int i = 0; i < photoCount; i++) {
				ProductPhotoGroup productPhotoGroup = new ProductPhotoGroup();
				product.getProductPhotoGroups().add(productPhotoGroup);
				String photoName = "photos/" + (index++ % 16 + 1) + ".jpg";
				try (InputStream photoStream = this.getClass().getResourceAsStream(photoName)) {
					byte[] photoBytes = IOUtils.toByteArray(photoStream);
					allCreatedEntities.add(productPhotoGroupRepository.generatePhotoGroup(productPhotoGroup, photoBytes));
				} catch (IOException ex) {
					throw new CommonPersistenceException("Can't store the photos into product.", ex);
				}
			}
		}
	}

	/*
	 * Metadata Information
	 */

	@Override
	protected EntityFactory<?>[] getDependEntityFactories() {
		return new EntityFactory<?>[] { productEntityFactory };
	}

	@Override
	public Class<ProductPhotoGroup> getEntityType() {
		return ProductPhotoGroup.class;
	}

	@Override
	protected JpaRepository<ProductPhotoGroup, Long> getGeneralJpaRepository() {
		return productPhotoGroupRepository;
	}
}

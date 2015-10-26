package priv.diouf.tengyi.distributor.persistence.initializer.factories.product;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.common.auxiliaries.ImageHelper;
import priv.diouf.tengyi.distributor.common.models.enums.PhotoFormat;
import priv.diouf.tengyi.distributor.common.models.enums.PhotoType;
import priv.diouf.tengyi.distributor.persistence.exceptions.CommonPersistenceException;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.EntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.GeneralEntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.TransactionalDataEntityFactory;
import priv.diouf.tengyi.distributor.persistence.models.photo.FullScreenPhoto;
import priv.diouf.tengyi.distributor.persistence.models.photo.OriginalPhoto;
import priv.diouf.tengyi.distributor.persistence.models.photo.ProductPhotoGroup;
import priv.diouf.tengyi.distributor.persistence.models.photo.StandardPhoto;
import priv.diouf.tengyi.distributor.persistence.models.photo.ThumbnailPhoto;
import priv.diouf.tengyi.distributor.persistence.models.product.Product;
import priv.diouf.tengyi.distributor.persistence.repositories.product.ProductPhotoGroupRepository;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ProductPhotoGroupEntityFactory extends GeneralEntityFactory<ProductPhotoGroup> implements TransactionalDataEntityFactory<ProductPhotoGroup> {

	/*
	 * Fields
	 */

	@Autowired
	protected ProductPhotoGroupRepository ProductPhotoGroupRepository;

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
			int photoCount = RandomUtils.nextInt(1, 12);
			for (int i = 0; i < photoCount; i++) {
				ProductPhotoGroup productPhotoGroup = new ProductPhotoGroup();
				product.getProductPhotoGroups().add(productPhotoGroup);
				String photoName = "resources/" + (index++ % 42 + 1) + ".jpg";
				try (InputStream dishStream = this.getClass().getResourceAsStream(photoName)) {
					if (dishStream != null && dishStream.available() > 0) {
						byte[] dishBytes = IOUtils.toByteArray(dishStream);
						// - Photo - ORIGINAL
						OriginalPhoto originalPhoto = new OriginalPhoto();
						originalPhoto.setVersion(UUID.randomUUID().toString());
						originalPhoto.setPhotoFormat(PhotoFormat.JPG);
						originalPhoto.setType(PhotoType.ORIGINAL);
						originalPhoto.setContent(dishBytes);
						productPhotoGroup.setOriginalPhoto(originalPhoto);

						// - Photo - THUMBNAIL
						ThumbnailPhoto thumbnailPhoto = new ThumbnailPhoto();
						thumbnailPhoto.setVersion(UUID.randomUUID().toString());
						thumbnailPhoto.setPhotoFormat(PhotoFormat.JPG);
						thumbnailPhoto.setType(PhotoType.THUMBNAIL);
						thumbnailPhoto.setContent(ImageHelper.compress(dishBytes, PhotoType.THUMBNAIL.getWidth(), PhotoType.THUMBNAIL
								.getHeight()));
						productPhotoGroup.setThumbnailPhoto(thumbnailPhoto);

						// - Photo - STANDARD
						StandardPhoto standardPhoto = new StandardPhoto();
						standardPhoto.setVersion(UUID.randomUUID().toString());
						standardPhoto.setPhotoFormat(PhotoFormat.JPG);
						standardPhoto.setType(PhotoType.STANDARD);
						standardPhoto.setContent(ImageHelper.compress(dishBytes, PhotoType.STANDARD.getWidth(), PhotoType.STANDARD
								.getHeight()));
						productPhotoGroup.setStandardPhoto(standardPhoto);

						// - Photo - FULL_SCREEN
						FullScreenPhoto fullScreenPhoto = new FullScreenPhoto();
						fullScreenPhoto.setVersion(UUID.randomUUID().toString());
						fullScreenPhoto.setPhotoFormat(PhotoFormat.JPG);
						fullScreenPhoto.setType(PhotoType.FULL_SCREEN);
						fullScreenPhoto.setContent(ImageHelper.compress(dishBytes, PhotoType.FULL_SCREEN.getWidth(), PhotoType.FULL_SCREEN
								.getHeight()));
						productPhotoGroup.setFullScreenPhoto(fullScreenPhoto);
						// Add Photo Group
						allCreatedEntities.add(productPhotoGroup);
					}
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
		return ProductPhotoGroupRepository;
	}
}

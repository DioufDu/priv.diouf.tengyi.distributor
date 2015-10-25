package priv.diouf.tengyi.distributor.persistence.initializer.factories;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.common.auxiliaries.ImageHelper;
import priv.diouf.tengyi.distributor.common.model.PhotoFormat;
import priv.diouf.tengyi.distributor.common.model.PhotoType;
import priv.diouf.tengyi.distributor.persistence.exceptions.CommonPersistenceException;
import priv.diouf.tengyi.distributor.persistence.models.Dish;
import priv.diouf.tengyi.distributor.persistence.models.FullScreenPhoto;
import priv.diouf.tengyi.distributor.persistence.models.OriginalPhoto;
import priv.diouf.tengyi.distributor.persistence.models.Photo;
import priv.diouf.tengyi.distributor.persistence.models.StandardPhoto;
import priv.diouf.tengyi.distributor.persistence.models.ThumbnailPhoto;
import priv.diouf.tengyi.distributor.persistence.repositories.DishRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.PhotoRepository;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PhotoEntityFactory extends GeneralEntityFactory<Photo> implements TransactionalDataEntityFactory<Photo> {

	/*
	 * Fields
	 */

	@Autowired
	protected PhotoRepository photoRepository;

	@Autowired
	protected DishRepository dishRepository;

	@Autowired
	protected DishEntityFactory dishEntityFactory;

	/*
	 * Creations
	 */

	@Override
	@Transactional
	protected void fulfill(List<Photo> allCreatedEntities) {
		int index = 0;
		for (Dish dish : dishEntityFactory.getAll()) {
			String photoName = "resources/" + (index++ % 42 + 1) + ".jpg";
			try (InputStream dishStream = this.getClass().getResourceAsStream(photoName)) {
				if (dishStream != null && dishStream.available() > 0) {
					byte[] dishBytes = IOUtils.toByteArray(dishStream);
					// - Photo
					// - Photo - ORIGINAL
					OriginalPhoto originalPhoto = new OriginalPhoto();
					originalPhoto.setVersion(UUID.randomUUID().toString());
					originalPhoto.setPhotoFormat(PhotoFormat.JPG);
					originalPhoto.setPhotoType(PhotoType.ORIGINAL);
					originalPhoto.setContent(dishBytes);
					dish.setOriginalPhoto(originalPhoto);
					allCreatedEntities.add(originalPhoto);
					// - Photo - THUMBNAIL
					ThumbnailPhoto thumbnailPhoto = new ThumbnailPhoto();
					thumbnailPhoto.setVersion(UUID.randomUUID().toString());
					thumbnailPhoto.setPhotoFormat(PhotoFormat.JPG);
					thumbnailPhoto.setPhotoType(PhotoType.THUMBNAIL);
					thumbnailPhoto.setContent(ImageHelper.compress(dishBytes, PhotoType.THUMBNAIL.getWidth(), PhotoType.THUMBNAIL
							.getHeight()));
					dish.setThumbnailPhoto(thumbnailPhoto);
					allCreatedEntities.add(thumbnailPhoto);
					// - Photo - STANDARD
					StandardPhoto standardPhoto = new StandardPhoto();
					standardPhoto.setVersion(UUID.randomUUID().toString());
					standardPhoto.setPhotoFormat(PhotoFormat.JPG);
					standardPhoto.setPhotoType(PhotoType.STANDARD);
					standardPhoto
					.setContent(ImageHelper.compress(dishBytes, PhotoType.STANDARD.getWidth(), PhotoType.STANDARD.getHeight()));
					dish.setStandardPhoto(standardPhoto);
					allCreatedEntities.add(standardPhoto);
					// - Photo - FULL_SCREEN
					FullScreenPhoto fullScreenPhoto = new FullScreenPhoto();
					fullScreenPhoto.setVersion(UUID.randomUUID().toString());
					fullScreenPhoto.setPhotoFormat(PhotoFormat.JPG);
					fullScreenPhoto.setPhotoType(PhotoType.FULL_SCREEN);
					fullScreenPhoto.setContent(ImageHelper.compress(dishBytes, PhotoType.FULL_SCREEN.getWidth(), PhotoType.FULL_SCREEN
							.getHeight()));
					dish.setFullScreenPhoto(fullScreenPhoto);
					allCreatedEntities.add(fullScreenPhoto);
				}
			} catch (IOException ex) {
				throw new CommonPersistenceException("Can't store the photo into dish.", ex);
			}
		}
		dishRepository.saveAndFlush(dishEntityFactory.getAll());
	}

	/*
	 * Metadata Information
	 */

	@Override
	protected EntityFactory<?>[] getDependEntityFactories() {
		return new EntityFactory<?>[] { dishEntityFactory };
	}

	@Override
	public Class<Photo> getEntityType() {
		return Photo.class;
	}

	@Override
	protected JpaRepository<Photo, Long> getGeneralJpaRepository() {
		return photoRepository;
	}
}

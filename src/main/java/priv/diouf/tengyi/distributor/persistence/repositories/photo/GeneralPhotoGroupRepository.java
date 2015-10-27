package priv.diouf.tengyi.distributor.persistence.repositories.photo;

import java.io.IOException;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.common.auxiliaries.ImageHelper;
import priv.diouf.tengyi.distributor.common.models.enums.PhotoFormat;
import priv.diouf.tengyi.distributor.common.models.enums.PhotoType;
import priv.diouf.tengyi.distributor.persistence.models.photo.FullScreenPhoto;
import priv.diouf.tengyi.distributor.persistence.models.photo.OriginalPhoto;
import priv.diouf.tengyi.distributor.persistence.models.photo.PhotoGroup;
import priv.diouf.tengyi.distributor.persistence.models.photo.PhotoGroup_;
import priv.diouf.tengyi.distributor.persistence.models.photo.StandardPhoto;
import priv.diouf.tengyi.distributor.persistence.models.photo.ThumbnailPhoto;
import priv.diouf.tengyi.distributor.persistence.repositories.GeneralJpaRepository;

@NoRepositoryBean
@Transactional
public class GeneralPhotoGroupRepository<TPhotoGroup extends PhotoGroup, TPhotoGroup_ extends PhotoGroup_> extends GeneralJpaRepository<TPhotoGroup, TPhotoGroup_, Long> {

	/*
	 * Constructors
	 */

	public GeneralPhotoGroupRepository(Class<TPhotoGroup> PhotoGroupClass, EntityManager entityManager) {
		super(PhotoGroupClass, entityManager);
	}

	/*
	 * Actions
	 */

	public TPhotoGroup generatePhotoGroup(TPhotoGroup photoGroup, byte[] imageBytes) throws IOException {
		if (imageBytes == null || imageBytes.length == 0) {
			return null;
		}
		// - Photo - ORIGINAL
		OriginalPhoto originalPhoto = new OriginalPhoto();
		originalPhoto.setVersion(UUID.randomUUID().toString());
		originalPhoto.setPhotoFormat(PhotoFormat.JPG);
		originalPhoto.setType(PhotoType.ORIGINAL);
		originalPhoto.setContent(imageBytes);
		photoGroup.setOriginalPhoto(originalPhoto);
		// - Photo - THUMBNAIL
		ThumbnailPhoto thumbnailPhoto = new ThumbnailPhoto();
		thumbnailPhoto.setVersion(UUID.randomUUID().toString());
		thumbnailPhoto.setPhotoFormat(PhotoFormat.JPG);
		thumbnailPhoto.setType(PhotoType.THUMBNAIL);
		thumbnailPhoto.setContent(ImageHelper.compress(imageBytes, PhotoType.THUMBNAIL.getWidth(), PhotoType.THUMBNAIL.getHeight()));
		photoGroup.setThumbnailPhoto(thumbnailPhoto);
		// - Photo - STANDARD
		StandardPhoto standardPhoto = new StandardPhoto();
		standardPhoto.setVersion(UUID.randomUUID().toString());
		standardPhoto.setPhotoFormat(PhotoFormat.JPG);
		standardPhoto.setType(PhotoType.STANDARD);
		standardPhoto.setContent(ImageHelper.compress(imageBytes, PhotoType.STANDARD.getWidth(), PhotoType.STANDARD.getHeight()));
		photoGroup.setStandardPhoto(standardPhoto);
		// - Photo - FULL_SCREEN
		FullScreenPhoto fullScreenPhoto = new FullScreenPhoto();
		fullScreenPhoto.setVersion(UUID.randomUUID().toString());
		fullScreenPhoto.setPhotoFormat(PhotoFormat.JPG);
		fullScreenPhoto.setType(PhotoType.FULL_SCREEN);
		fullScreenPhoto.setContent(ImageHelper.compress(imageBytes, PhotoType.FULL_SCREEN.getWidth(), PhotoType.FULL_SCREEN.getHeight()));
		photoGroup.setFullScreenPhoto(fullScreenPhoto);
		// Return
		return photoGroup;
	}
}

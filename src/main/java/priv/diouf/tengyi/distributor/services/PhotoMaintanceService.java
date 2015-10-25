package priv.diouf.tengyi.distributor.services;

import java.io.IOException;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.common.auxiliaries.ImageHelper;
import priv.diouf.tengyi.distributor.common.model.PhotoFormat;
import priv.diouf.tengyi.distributor.common.model.PhotoType;
import priv.diouf.tengyi.distributor.persistence.models.Dish;
import priv.diouf.tengyi.distributor.persistence.models.FullScreenPhoto;
import priv.diouf.tengyi.distributor.persistence.models.OriginalPhoto;
import priv.diouf.tengyi.distributor.persistence.models.StandardPhoto;
import priv.diouf.tengyi.distributor.persistence.models.ThumbnailPhoto;
import priv.diouf.tengyi.distributor.persistence.repositories.DishRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.FullScreenPhotoRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.OriginalPhotoRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.PhotoRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.StandardPhotoRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.ThumbnailPhotoRepository;
import priv.diouf.tengyi.distributor.web.models.responses.photo.PhotoIdCollection;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PhotoMaintanceService {

	/*
	 * Fields
	 */

	@Autowired
	protected DishRepository dishRepository;

	@Autowired
	protected PhotoRepository photoRepository;

	@Autowired
	protected OriginalPhotoRepository originalPhotoRepository;

	@Autowired
	protected ThumbnailPhotoRepository thumbnailPhotoRepository;

	@Autowired
	protected StandardPhotoRepository standardPhotoRepository;

	@Autowired
	protected FullScreenPhotoRepository fullScreenPhotoRepository;

	/*
	 * CUD Actions
	 */

	@Transactional
	public void migratePhotos(@Valid @NotNull PhotoIdCollection photoIds, @Valid @NotNull Dish dish) {
		// - Original Photo
		OriginalPhoto originalPhoto = originalPhotoRepository.findOne(photoIds.getOriginalPhotoId());
		int angle = photoIds.getAngle() % 360;
		dish.setOriginalPhoto(originalPhoto);
		if (angle != 0) {
			originalPhoto.setContent(ImageHelper.transferImage(originalPhoto, angle));
		}
		// - Thumbnail Photo
		ThumbnailPhoto thumbnailPhoto = thumbnailPhotoRepository.findOne(photoIds.getThumbnailPhotoId());
		dish.setThumbnailPhoto(thumbnailPhoto);
		if (angle != 0) {
			thumbnailPhoto.setContent(ImageHelper.transferImage(thumbnailPhoto, angle));
		}
		// - Standard Photo
		StandardPhoto standardPhoto = standardPhotoRepository.findOne(photoIds.getStandardPhotoId());
		if (angle != 0) {
			standardPhoto.setContent(ImageHelper.transferImage(standardPhoto, angle));
		}
		dish.setStandardPhoto(standardPhoto);

		// - Full Screen Photo
		FullScreenPhoto fullScreenPhoto = fullScreenPhotoRepository.findOne(photoIds.getFullScreenPhotoId());
		if (angle != 0) {
			fullScreenPhoto.setContent(ImageHelper.transferImage(fullScreenPhoto, angle));
		}
		dish.setFullScreenPhoto(fullScreenPhoto);

	}

	@Transactional
	public PhotoIdCollection generatePhotos(byte[] photoBytes, PhotoFormat photoFormat) throws IOException {
		// - Photo
		OriginalPhoto originalPhoto = generateOriginalPhoto(photoBytes, photoFormat);
		ThumbnailPhoto thumbnailPhoto = generateThumbnailPhoto(photoBytes, photoFormat);
		StandardPhoto standardPhoto = generateStandardPhoto(photoBytes, photoFormat);
		FullScreenPhoto fullScreenPhoto = generateFullScreenPhoto(photoBytes, photoFormat);
		// Save
		photoRepository.saveAndFlush(originalPhoto, thumbnailPhoto, standardPhoto, fullScreenPhoto);
		// Collect Ids
		PhotoIdCollection photoIdCollection = new PhotoIdCollection();
		photoIdCollection.setOriginalPhotoId(originalPhoto.getId());
		photoIdCollection.setThumbnailPhotoId(thumbnailPhoto.getId());
		photoIdCollection.setStandardPhotoId(standardPhoto.getId());
		photoIdCollection.setFullScreenPhotoId(fullScreenPhoto.getId());
		return photoIdCollection;
	}

	@Transactional
	public void generatePhotos(byte[] originalPhotoBytes, PhotoFormat photoFormat, Dish dish) throws IOException {
		OriginalPhoto originalPhoto = generateOriginalPhoto(originalPhotoBytes, photoFormat);
		dish.setOriginalPhoto(originalPhoto);
		ThumbnailPhoto thumbnailPhoto = generateThumbnailPhoto(originalPhotoBytes, photoFormat);
		dish.setThumbnailPhoto(thumbnailPhoto);
		StandardPhoto standardPhoto = generateStandardPhoto(originalPhotoBytes, photoFormat);
		dish.setStandardPhoto(standardPhoto);
		FullScreenPhoto fullScreenPhoto = generateFullScreenPhoto(originalPhotoBytes, photoFormat);
		dish.setFullScreenPhoto(fullScreenPhoto);
		;
	}

	@Transactional
	public OriginalPhoto generateOriginalPhoto(byte[] photoBytes, PhotoFormat photoFormat) {
		// - Photo - ORIGINAL
		OriginalPhoto originalPhoto = new OriginalPhoto();
		originalPhoto.setVersion(UUID.randomUUID().toString());
		originalPhoto.setPhotoFormat(photoFormat);
		originalPhoto.setPhotoType(PhotoType.ORIGINAL);
		originalPhoto.setContent(photoBytes);
		return originalPhoto;
	}

	@Transactional
	public FullScreenPhoto generateFullScreenPhoto(byte[] photoBytes, PhotoFormat photoFormat) throws IOException {
		FullScreenPhoto fullScreenPhoto = new FullScreenPhoto();
		fullScreenPhoto.setVersion(UUID.randomUUID().toString());
		fullScreenPhoto.setPhotoFormat(photoFormat);
		fullScreenPhoto.setPhotoType(PhotoType.FULL_SCREEN);
		fullScreenPhoto.setContent(ImageHelper.compress(photoBytes, PhotoType.FULL_SCREEN.getWidth(), PhotoType.FULL_SCREEN.getHeight()));
		return fullScreenPhoto;
	}

	@Transactional
	public StandardPhoto generateStandardPhoto(byte[] photoBytes, PhotoFormat photoFormat) throws IOException {
		StandardPhoto standardPhoto = new StandardPhoto();
		standardPhoto.setVersion(UUID.randomUUID().toString());
		standardPhoto.setPhotoFormat(photoFormat);
		standardPhoto.setPhotoType(PhotoType.STANDARD);
		standardPhoto.setContent(ImageHelper.compress(photoBytes, PhotoType.STANDARD.getWidth(), PhotoType.STANDARD.getHeight()));
		return standardPhoto;
	}

	@Transactional
	public ThumbnailPhoto generateThumbnailPhoto(byte[] photoBytes, PhotoFormat photoFormat) throws IOException {
		ThumbnailPhoto thumbnailPhoto = new ThumbnailPhoto();
		thumbnailPhoto.setVersion(UUID.randomUUID().toString());
		thumbnailPhoto.setPhotoFormat(photoFormat);
		thumbnailPhoto.setPhotoType(PhotoType.THUMBNAIL);
		thumbnailPhoto.setContent(ImageHelper.compress(photoBytes, PhotoType.THUMBNAIL.getWidth(), PhotoType.THUMBNAIL.getHeight()));
		return thumbnailPhoto;
	}

	@Transactional
	public void deletePhotos(Dish dish) {
		photoRepository.delete(dish.getThumbnailPhoto());
		photoRepository.delete(dish.getStandardPhoto());
		photoRepository.delete(dish.getFullScreenPhoto());
		photoRepository.delete(dish.getOriginalPhoto());
	}

	/*
	 * Private & Protected Methods
	 */

}

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
import priv.diouf.tengyi.distributor.common.models.enums.PhotoFormat;
import priv.diouf.tengyi.distributor.common.models.enums.PhotoType;
import priv.diouf.tengyi.distributor.persistence.models.photo.FullScreenPhoto;
import priv.diouf.tengyi.distributor.persistence.models.photo.OriginalPhoto;
import priv.diouf.tengyi.distributor.persistence.models.photo.PhotoGroup;
import priv.diouf.tengyi.distributor.persistence.models.photo.StandardPhoto;
import priv.diouf.tengyi.distributor.persistence.models.photo.ThumbnailPhoto;
import priv.diouf.tengyi.distributor.persistence.repositories.photo.FullScreenPhotoRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.photo.OriginalPhotoRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.photo.PhotoRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.photo.StandardPhotoRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.photo.ThumbnailPhotoRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.product.ProductRepository;
import priv.diouf.tengyi.distributor.web.models.responses.photo.PhotoGroupInfo;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PhotoMaintanceService {

	/*
	 * Fields
	 */

	@Autowired
	protected ProductRepository productRepository;

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
	public void migratePhotos(@Valid @NotNull PhotoGroupInfo photoIds, @Valid @NotNull PhotoGroup photoGroup) {
		// - Original Photo
		OriginalPhoto originalPhoto = originalPhotoRepository.findOne(photoIds.getOriginalPhotoId());
		int angle = photoIds.getAngle() % 360;
		photoGroup.setOriginalPhoto(originalPhoto);
		if (angle != 0) {
			originalPhoto.setContent(ImageHelper.transferImage(originalPhoto, angle));
		}
		// - Thumbnail Photo
		ThumbnailPhoto thumbnailPhoto = thumbnailPhotoRepository.findOne(photoIds.getThumbnailPhotoId());
		photoGroup.setThumbnailPhoto(thumbnailPhoto);
		if (angle != 0) {
			thumbnailPhoto.setContent(ImageHelper.transferImage(thumbnailPhoto, angle));
		}
		// - Standard Photo
		StandardPhoto standardPhoto = standardPhotoRepository.findOne(photoIds.getStandardPhotoId());
		if (angle != 0) {
			standardPhoto.setContent(ImageHelper.transferImage(standardPhoto, angle));
		}
		photoGroup.setStandardPhoto(standardPhoto);

		// - Full Screen Photo
		FullScreenPhoto fullScreenPhoto = fullScreenPhotoRepository.findOne(photoIds.getFullScreenPhotoId());
		if (angle != 0) {
			fullScreenPhoto.setContent(ImageHelper.transferImage(fullScreenPhoto, angle));
		}
		photoGroup.setFullScreenPhoto(fullScreenPhoto);

	}

	@Transactional
	public PhotoGroupInfo generatePhotos(byte[] photoBytes, PhotoFormat photoFormat) throws IOException {
		// - Photo
		OriginalPhoto originalPhoto = generateOriginalPhoto(photoBytes, photoFormat);
		ThumbnailPhoto thumbnailPhoto = generateThumbnailPhoto(photoBytes, photoFormat);
		StandardPhoto standardPhoto = generateStandardPhoto(photoBytes, photoFormat);
		FullScreenPhoto fullScreenPhoto = generateFullScreenPhoto(photoBytes, photoFormat);
		// Save
		photoRepository.saveAndFlush(originalPhoto, thumbnailPhoto, standardPhoto, fullScreenPhoto);
		// Collect Ids
		PhotoGroupInfo photoIdCollection = new PhotoGroupInfo();
		photoIdCollection.setOriginalPhotoId(originalPhoto.getId());
		photoIdCollection.setThumbnailPhotoId(thumbnailPhoto.getId());
		photoIdCollection.setStandardPhotoId(standardPhoto.getId());
		photoIdCollection.setFullScreenPhotoId(fullScreenPhoto.getId());
		return photoIdCollection;
	}

	@Transactional
	public void generatePhotos(byte[] originalPhotoBytes, PhotoFormat photoFormat, PhotoGroup photoGroup) throws IOException {
		OriginalPhoto originalPhoto = generateOriginalPhoto(originalPhotoBytes, photoFormat);
		photoGroup.setOriginalPhoto(originalPhoto);
		ThumbnailPhoto thumbnailPhoto = generateThumbnailPhoto(originalPhotoBytes, photoFormat);
		photoGroup.setThumbnailPhoto(thumbnailPhoto);
		StandardPhoto standardPhoto = generateStandardPhoto(originalPhotoBytes, photoFormat);
		photoGroup.setStandardPhoto(standardPhoto);
		FullScreenPhoto fullScreenPhoto = generateFullScreenPhoto(originalPhotoBytes, photoFormat);
		photoGroup.setFullScreenPhoto(fullScreenPhoto);
	}

	@Transactional
	public OriginalPhoto generateOriginalPhoto(byte[] photoBytes, PhotoFormat photoFormat) {
		// - Photo - ORIGINAL
		OriginalPhoto originalPhoto = new OriginalPhoto();
		originalPhoto.setVersion(UUID.randomUUID().toString());
		originalPhoto.setPhotoFormat(photoFormat);
		originalPhoto.setType(PhotoType.ORIGINAL);
		originalPhoto.setContent(photoBytes);
		return originalPhoto;
	}

	@Transactional
	public FullScreenPhoto generateFullScreenPhoto(byte[] photoBytes, PhotoFormat photoFormat) throws IOException {
		FullScreenPhoto fullScreenPhoto = new FullScreenPhoto();
		fullScreenPhoto.setVersion(UUID.randomUUID().toString());
		fullScreenPhoto.setPhotoFormat(photoFormat);
		fullScreenPhoto.setType(PhotoType.FULL_SCREEN);
		fullScreenPhoto.setContent(ImageHelper.compress(photoBytes, PhotoType.FULL_SCREEN.getWidth(), PhotoType.FULL_SCREEN.getHeight()));
		return fullScreenPhoto;
	}

	@Transactional
	public StandardPhoto generateStandardPhoto(byte[] photoBytes, PhotoFormat photoFormat) throws IOException {
		StandardPhoto standardPhoto = new StandardPhoto();
		standardPhoto.setVersion(UUID.randomUUID().toString());
		standardPhoto.setPhotoFormat(photoFormat);
		standardPhoto.setType(PhotoType.STANDARD);
		standardPhoto.setContent(ImageHelper.compress(photoBytes, PhotoType.STANDARD.getWidth(), PhotoType.STANDARD.getHeight()));
		return standardPhoto;
	}

	@Transactional
	public ThumbnailPhoto generateThumbnailPhoto(byte[] photoBytes, PhotoFormat photoFormat) throws IOException {
		ThumbnailPhoto thumbnailPhoto = new ThumbnailPhoto();
		thumbnailPhoto.setVersion(UUID.randomUUID().toString());
		thumbnailPhoto.setPhotoFormat(photoFormat);
		thumbnailPhoto.setType(PhotoType.THUMBNAIL);
		thumbnailPhoto.setContent(ImageHelper.compress(photoBytes, PhotoType.THUMBNAIL.getWidth(), PhotoType.THUMBNAIL.getHeight()));
		return thumbnailPhoto;
	}

	@Transactional
	public void deletePhotos(PhotoGroup photoGroup) {
		photoRepository.delete(photoGroup.getThumbnailPhoto());
		photoRepository.delete(photoGroup.getStandardPhoto());
		photoRepository.delete(photoGroup.getFullScreenPhoto());
		photoRepository.delete(photoGroup.getOriginalPhoto());
	}

	/*
	 * Private & Protected Methods
	 */

}

package priv.diouf.tengyi.distributor.web.models.responses.photo;

import java.io.Serializable;

import priv.diouf.tengyi.distributor.persistence.models.photo.PhotoGroup;

public class PhotoGroupInfo implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Fields
	 */

	protected Long thumbnailPhotoId;

	protected Long fullScreenPhotoId;

	/*
	 * Constructors
	 */

	public PhotoGroupInfo() {

	}

	public PhotoGroupInfo(PhotoGroup photoGroup) {
		if (photoGroup == null) {
			return;
		}
		if (photoGroup.getThumbnailPhoto() != null) {
			this.setThumbnailPhotoId(photoGroup.getThumbnailPhoto().getId());
		}
		if (photoGroup.getFullScreenPhoto() != null) {
			this.setFullScreenPhotoId(photoGroup.getFullScreenPhoto().getId());
		}
	}

	/*
	 * Properties
	 */

	public Long getThumbnailPhotoId() {
		return thumbnailPhotoId;
	}

	public void setThumbnailPhotoId(Long thumbnailPhotoId) {
		this.thumbnailPhotoId = thumbnailPhotoId;
	}

	public Long getFullScreenPhotoId() {
		return fullScreenPhotoId;
	}

	public void setFullScreenPhotoId(Long fullScreenPhotoId) {
		this.fullScreenPhotoId = fullScreenPhotoId;
	}
}

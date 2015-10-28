package priv.diouf.tengyi.distributor.web.models.responses.photo;

import java.io.Serializable;

import priv.diouf.tengyi.distributor.persistence.models.photo.PhotoGroup;

public class PhotoGroupDetail extends PhotoGroupInfo implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Fields
	 */

	protected Long originalPhotoId;

	protected Long standardPhotoId;

	/*
	 * Constructors
	 */

	public PhotoGroupDetail() {

	}

	public PhotoGroupDetail(PhotoGroup photoGroup) {
		super(photoGroup);
		if (photoGroup == null) {
			return;
		}
		if (photoGroup.getOriginalPhoto() != null) {
			this.setOriginalPhotoId(photoGroup.getOriginalPhoto().getId());
		}
		if (photoGroup.getStandardPhoto() != null) {
			this.setStandardPhotoId(photoGroup.getStandardPhoto().getId());
		}
	}

	/*
	 * Properties
	 */

	public Long getOriginalPhotoId() {
		return originalPhotoId;
	}

	public void setOriginalPhotoId(Long originalPhotoId) {
		this.originalPhotoId = originalPhotoId;
	}

	public Long getStandardPhotoId() {
		return standardPhotoId;
	}

	public void setStandardPhotoId(Long standardPhotoId) {
		this.standardPhotoId = standardPhotoId;
	}
}

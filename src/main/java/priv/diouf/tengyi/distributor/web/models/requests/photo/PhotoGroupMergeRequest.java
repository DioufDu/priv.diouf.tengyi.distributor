package priv.diouf.tengyi.distributor.web.models.requests.photo;

import java.io.Serializable;

public class PhotoGroupMergeRequest implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Fields
	 */

	protected Long originalPhotoId;

	protected Long thumbnailPhotoId;

	protected Long standardPhotoId;

	protected Long fullScreenPhotoId;

	protected int angle;

	/*
	 * Constructors
	 */

	public PhotoGroupMergeRequest() {

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

	public Long getThumbnailPhotoId() {
		return thumbnailPhotoId;
	}

	public void setThumbnailPhotoId(Long thumbnailPhotoId) {
		this.thumbnailPhotoId = thumbnailPhotoId;
	}

	public Long getStandardPhotoId() {
		return standardPhotoId;
	}

	public void setStandardPhotoId(Long standardPhotoId) {
		this.standardPhotoId = standardPhotoId;
	}

	public Long getFullScreenPhotoId() {
		return fullScreenPhotoId;
	}

	public void setFullScreenPhotoId(Long fullScreenPhotoId) {
		this.fullScreenPhotoId = fullScreenPhotoId;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}
}

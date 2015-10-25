package priv.diouf.tengyi.distributor.web.models.responses.photo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import priv.diouf.tengyi.distributor.persistence.models.Dish;

public class PhotoIdCollection implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -7815758842712693811L;

	/*
	 * Fields
	 */

	@JsonProperty("originalPhotoId")
	protected Long originalPhotoId;

	@JsonProperty("thumbnailPhotoId")
	protected Long thumbnailPhotoId;

	@JsonProperty("standardPhotoId")
	protected Long standardPhotoId;

	@JsonProperty("fullScreenPhotoId")
	protected Long fullScreenPhotoId;

	@JsonProperty("angle")
	protected int angle;

	/*
	 * Constructors
	 */

	public PhotoIdCollection() {

	}

	public PhotoIdCollection(Dish dish) {
		if (dish == null) {
			return;
		}
		if (dish.getOriginalPhoto() != null) {
			this.setOriginalPhotoId(dish.getOriginalPhoto().getId());
		}
		if (dish.getThumbnailPhoto() != null) {
			this.setThumbnailPhotoId(dish.getThumbnailPhoto().getId());
		}
		if (dish.getStandardPhoto() != null) {
			this.setStandardPhotoId(dish.getStandardPhoto().getId());
		}
		if (dish.getFullScreenPhoto() != null) {
			this.setFullScreenPhotoId(dish.getFullScreenPhoto().getId());
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

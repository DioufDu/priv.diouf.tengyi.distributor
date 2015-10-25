package priv.diouf.tengyi.distributor.web.models.requests.dish;

import java.io.Serializable;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonProperty;

import priv.diouf.tengyi.distributor.common.model.PhotoFormat;
import priv.diouf.tengyi.distributor.web.models.responses.photo.PhotoIdCollection;

public class CreationRequest implements Serializable {

	private static final long serialVersionUID = -4958976776470575856L;

	/*
	 * Fields
	 */

	@JsonProperty("chineseName")
	protected String chineseName;

	@JsonProperty("englishName")
	protected String englishName;

	@JsonProperty("line")
	protected String line;

	@JsonProperty("offerDate")
	protected Calendar offerDate;

	@JsonProperty("isRecommended")
	protected boolean isRecommended;

	@JsonProperty("recommendedReason")
	protected String recommendedReason;

	@JsonProperty("description")
	protected String description;

	@JsonProperty("photoFormat")
	protected PhotoFormat photoFormat;

	@JsonProperty("photo")
	protected PhotoIdCollection photo;

	/*
	 * Properties
	 */

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public Calendar getOfferDate() {
		return offerDate;
	}

	public void setOfferDate(Calendar offerDate) {
		this.offerDate = offerDate;
	}

	public boolean isRecommended() {
		return isRecommended;
	}

	public void setRecommended(boolean isRecommended) {
		this.isRecommended = isRecommended;
	}

	public String getRecommendedReason() {
		return recommendedReason;
	}

	public void setRecommendedReason(String recommendedReason) {
		this.recommendedReason = recommendedReason;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PhotoFormat getPhotoFormat() {
		return photoFormat;
	}

	public void setPhotoFormat(PhotoFormat photoFormat) {
		this.photoFormat = photoFormat;
	}

	public PhotoIdCollection getPhoto() {
		return photo;
	}

	public void setPhoto(PhotoIdCollection photo) {
		this.photo = photo;
	}
}

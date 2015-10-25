package priv.diouf.tengyi.distributor.web.models.responses.dish;

import java.io.Serializable;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonProperty;

import priv.diouf.tengyi.distributor.persistence.models.Dish;
import priv.diouf.tengyi.distributor.web.models.responses.photo.PhotoIdCollection;
import priv.diouf.tengyi.distributor.web.models.responses.rate.RateGroupInfo;

public class DishDetail implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 6420012423956739503L;

	/*
	 * Fields
	 */

	@JsonProperty("id")
	protected Long id;

	@JsonProperty("chineseName")
	protected String chineseName;

	@JsonProperty("englishName")
	protected String englishName;

	@JsonProperty("line")
	protected String line;

	@JsonProperty("description")
	protected String description;

	@JsonProperty("offerDate")
	protected Calendar offerDate;

	@JsonProperty("isRecommended")
	protected boolean isRecommended;

	@JsonProperty("recommendedReason")
	protected String recommendedReason;

	@JsonProperty("photo")
	protected PhotoIdCollection photo;

	@JsonProperty("rate")
	protected RateGroupInfo rate;

	/*
	 * Constructors
	 */

	public DishDetail(Dish dish) {
		if (dish == null) {
			return;
		}
		this.setId(dish.getId());
		this.setChineseName(dish.getChineseName());
		this.setEnglishName(dish.getEnglishName());
		this.setLine(dish.getLine());
		this.setOfferDate(dish.getOfferDate());
		this.setDescription(dish.getDescription());
		this.setRecommended(dish.isRecommended());
		this.setRecommendedReason(dish.getRecommendedReason());
		this.setPhoto(new PhotoIdCollection(dish));
		this.rate = new RateGroupInfo(dish.getRateGroup());
	}

	/*
	 * Properties
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public PhotoIdCollection getPhoto() {
		return photo;
	}

	public void setPhoto(PhotoIdCollection photo) {
		this.photo = photo;
	}

	public RateGroupInfo getRate() {
		return rate;
	}

	public void setRate(RateGroupInfo rate) {
		this.rate = rate;
	}

}

package priv.diouf.tengyi.distributor.persistence.models;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import priv.diouf.tengyi.distributor.persistence.PersistenceConfig;

@Entity
@Table(name = "T_DISH", schema = PersistenceConfig.PERSISTENCE_SCHEMA_NAME)
public class Dish implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1908464406652655228L;

	/**
	 * ID
	 */

	@Id
	@GeneratedValue
	@Column(name = "ID")
	protected Long id;

	/*
	 * Fields
	 */

	@Column(name = "CHINESE_NAME", nullable = false)
	protected String chineseName;

	@Column(name = "ENGLISH_NAME", nullable = true)
	protected String englishName;

	@Column(name = "LINE", nullable = false)
	protected String line;

	@Column(name = "DESCRIPTION", nullable = true, length = 1000)
	protected String description;

	@Column(name = "OFFER_DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	protected Calendar offerDate;

	@Column(name = "IS_RECOMMENDED")
	protected boolean isRecommended;

	@Column(name = "RECOMMENDED_REASON")
	protected String recommendedReason;

	/*
	 * Embed
	 */

	@Embedded
	protected Modification modification;

	/*
	 * Navigations
	 */

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "RATE_GROUP_ID")
	protected RateGroup rateGroup;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "ORIGINAL_PHOTO_ID")
	protected OriginalPhoto originalPhoto;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "THUMBNAIL_PHOTO_ID")
	protected ThumbnailPhoto thumbnailPhoto;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "STANDARD_PHOTO_ID")
	protected StandardPhoto standardPhoto;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "FULL_SCREEN_PHOTO_ID")
	protected FullScreenPhoto fullScreenPhoto;

	/*
	 * Constructors
	 */

	public Dish() {
		this.setModification(new Modification());
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

	public Modification getModification() {
		return modification;
	}

	public void setModification(Modification modification) {
		this.modification = modification;
	}

	public RateGroup getRateGroup() {
		return rateGroup;
	}

	public void setRateGroup(RateGroup rateGroup) {
		this.rateGroup = rateGroup;
	}

	public OriginalPhoto getOriginalPhoto() {
		return originalPhoto;
	}

	public void setOriginalPhoto(OriginalPhoto originalPhoto) {
		this.originalPhoto = originalPhoto;
	}

	public ThumbnailPhoto getThumbnailPhoto() {
		return thumbnailPhoto;
	}

	public void setThumbnailPhoto(ThumbnailPhoto thumbnailPhoto) {
		this.thumbnailPhoto = thumbnailPhoto;
	}

	public StandardPhoto getStandardPhoto() {
		return standardPhoto;
	}

	public void setStandardPhoto(StandardPhoto standardPhoto) {
		this.standardPhoto = standardPhoto;
	}

	public FullScreenPhoto getFullScreenPhoto() {
		return fullScreenPhoto;
	}

	public void setFullScreenPhoto(FullScreenPhoto fullScreenPhoto) {
		this.fullScreenPhoto = fullScreenPhoto;
	}

}

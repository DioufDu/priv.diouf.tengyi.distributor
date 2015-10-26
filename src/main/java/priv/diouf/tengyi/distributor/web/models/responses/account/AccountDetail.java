package priv.diouf.tengyi.distributor.web.models.responses.account;

import java.io.Serializable;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonProperty;

import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.web.models.responses.photo.PhotoGroupInfo;

public class AccountDetail implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 6420012423956739503L;

	/*
	 * Fields
	 */

	@JsonProperty("id")
	protected long id;

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
	protected PhotoGroupInfo photo;

	/*
	 * Constructors
	 */

	public AccountDetail(Account account) {
		if (account == null) {
			return;
		}
		this.setId(account.getId());
		// TODO: Scalar Properties
	}

	/*
	 * Properties
	 */

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public PhotoGroupInfo getPhoto() {
		return photo;
	}

	public void setPhoto(PhotoGroupInfo photo) {
		this.photo = photo;
	}
}

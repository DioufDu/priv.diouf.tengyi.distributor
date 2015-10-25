package priv.diouf.tengyi.distributor.services.models;

import java.io.Serializable;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchByDateCriteria implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 3216173048516150330L;

	/*
	 * Fields
	 */

	@JsonProperty("offerDate")
	private Calendar offerDate;

	/*
	 * Properties
	 */

	public Calendar getOfferDate() {
		return offerDate;
	}

	public void setOfferDate(Calendar offerDate) {
		this.offerDate = offerDate;
	}

}

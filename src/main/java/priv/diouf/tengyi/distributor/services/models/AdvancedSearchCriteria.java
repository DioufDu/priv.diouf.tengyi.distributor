package priv.diouf.tengyi.distributor.services.models;

import java.io.Serializable;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdvancedSearchCriteria implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 14964103930726563L;

	/*
	 * Fields
	 */

	@JsonProperty("name")
	protected String name;

	@JsonProperty("line")
	protected String line;

	@JsonProperty("description")
	protected String description;

	@JsonProperty("startDate")
	protected Calendar startDate;

	@JsonProperty("endDate")
	protected Calendar endDate;

	@JsonProperty("isRecommended")
	protected boolean isRecommended;

	/*
	 * Properties
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public boolean isRecommended() {
		return isRecommended;
	}

	public void setRecommended(boolean isRecommended) {
		this.isRecommended = isRecommended;
	}

}

package priv.diouf.tengyi.distributor.services.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BasicSearchCriteria implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 2478380267747363563L;

	/*
	 * Fields
	 */

	@JsonProperty("keyword")
	protected String keyword;

	/*
	 * Properties
	 */

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}

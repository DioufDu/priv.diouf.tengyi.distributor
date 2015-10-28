package priv.diouf.tengyi.distributor.services.criterias.account;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountBasicSearchCriteria implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

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

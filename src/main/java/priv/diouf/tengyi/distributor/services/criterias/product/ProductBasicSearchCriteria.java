package priv.diouf.tengyi.distributor.services.criterias.product;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductBasicSearchCriteria implements Serializable {

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

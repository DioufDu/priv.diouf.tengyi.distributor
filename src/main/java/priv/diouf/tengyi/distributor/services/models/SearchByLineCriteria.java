package priv.diouf.tengyi.distributor.services.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchByLineCriteria implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 3216173048516150330L;

	/*
	 * Fields
	 */

	@JsonProperty("line")
	protected String line;

	/*
	 * Properties
	 */

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
}

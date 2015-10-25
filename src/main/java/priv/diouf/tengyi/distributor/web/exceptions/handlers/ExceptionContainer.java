package priv.diouf.tengyi.distributor.web.exceptions.handlers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionContainer<TBody> {

	/**
	 * Fields
	 */

	@JsonProperty("error")
	protected TBody body;

	/**
	 * Constructors
	 */

	public ExceptionContainer(TBody body) {
		this.body = body;
	}

	/**
	 * Properties
	 */

	public TBody getBody() {
		return this.body;
	}
}

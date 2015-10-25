package priv.diouf.tengyi.distributor.web.models.requests.dish;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RateRequest implements Serializable {

	/**
	 * Gened Serial Version UID
	 */
	private static final long serialVersionUID = -5330068018662256300L;

	/*
	 * Fields
	 */

	@JsonProperty("taste")
	@NotNull
	protected Integer taste;

	@JsonProperty("style")
	@NotNull
	protected Integer style;

	@JsonProperty("service")
	@NotNull
	protected Integer service;

	/*
	 * Properties
	 */

	public Integer getTaste() {
		return taste;
	}

	public void setTaste(Integer Taste) {
		this.taste = Taste;
	}

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer Style) {
		this.style = Style;
	}

	public Integer getService() {
		return service;
	}

	public void setService(Integer Service) {
		this.service = Service;
	}
}

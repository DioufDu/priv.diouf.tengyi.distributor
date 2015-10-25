package priv.diouf.tengyi.distributor.web.models.requests.dish;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateRequest extends CreationRequest implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 6362812806799594320L;

	/*
	 * Fields
	 */

	@JsonProperty("isPhotoRenewRequired")
	protected boolean isPhotoRenewRequired;

	/*
	 * Properties
	 */

	public boolean isPhotoRenewRequired() {
		return isPhotoRenewRequired;
	}

	public void setPhotoRenewRequired(boolean isPhotoRenewRequired) {
		this.isPhotoRenewRequired = isPhotoRenewRequired;
	}
}

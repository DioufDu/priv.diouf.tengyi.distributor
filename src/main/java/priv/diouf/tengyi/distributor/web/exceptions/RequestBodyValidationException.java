package priv.diouf.tengyi.distributor.web.exceptions;

import org.springframework.validation.BindingResult;

public class RequestBodyValidationException extends CommonWebException {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 8617177844267794294L;

	/*
	 * Fields
	 */

	protected static final String DEFAULT_DESCRIPTION = "There are some invalid values in request body.";

	protected String errorMessage;

	protected BindingResult bindingResult;

	/*
	 * Constructors
	 */

	public RequestBodyValidationException(BindingResult bindingResult) {
		super(DEFAULT_DESCRIPTION);
		this.bindingResult = bindingResult;
	}

	public RequestBodyValidationException(String errorMessage) {
		super(errorMessage);
	}

	public RequestBodyValidationException(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}

	/*
	 * Properties
	 */

	public BindingResult getBindingResult() {
		return bindingResult;
	}

	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}
}

package priv.diouf.tengyi.distributor.common.exceptions;

import org.apache.commons.lang3.StringUtils;

public class CommonApplicationException extends RuntimeException {

	/*
	 * Fields
	 */

	private static final long serialVersionUID = 1L;
	protected String errorMessage;

	/*
	 * Properties
	 */

	public CommonApplicationException(String errorMessage) {
		this.setErrorMessage(errorMessage);
	}

	public CommonApplicationException(String errorMessage, Exception ex) {
		super(ex);
		this.setErrorMessage(errorMessage);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	/*
	 * Constructors
	 */

	@Override
	public String getMessage() {
		if (StringUtils.isBlank(this.errorMessage)) {
			return super.getMessage();
		} else {
			return this.errorMessage;
		}
	}

	protected void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}

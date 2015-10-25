package priv.diouf.tengyi.distributor.web.exceptions;

import priv.diouf.tengyi.distributor.common.exceptions.CommonApplicationException;

public class CommonWebException extends CommonApplicationException {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -49093352328654622L;

	/*
	 * Constructors
	 */

	public CommonWebException(String errorMessage) {
		super(errorMessage);
	}

	public CommonWebException(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}

package priv.diouf.tengyi.distributor.services.exceptions;

import priv.diouf.tengyi.distributor.common.exceptions.CommonApplicationException;

public class CommonBusinessException extends CommonApplicationException {

	/**
	 * Generate Serial Version UID
	 */
	private static final long serialVersionUID = 2103369642877744564L;

	/*
	 * Constructors
	 */

	public CommonBusinessException(String errorMessage) {
		super(errorMessage);
	}

	public CommonBusinessException(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}

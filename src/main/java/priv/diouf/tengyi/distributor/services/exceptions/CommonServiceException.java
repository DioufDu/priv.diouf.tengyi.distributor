package priv.diouf.tengyi.distributor.services.exceptions;

import priv.diouf.tengyi.distributor.common.exceptions.CommonApplicationException;

public class CommonServiceException extends CommonApplicationException {
	private static final long serialVersionUID = 1L;

	public CommonServiceException(String errorMessage) {
		super(errorMessage);
	}

	public CommonServiceException(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}

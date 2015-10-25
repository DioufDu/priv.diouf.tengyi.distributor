package priv.diouf.tengyi.distributor.services.exceptions;

import priv.diouf.tengyi.distributor.common.exceptions.CommonApplicationException;

public class CommonDomainException extends CommonApplicationException {
	private static final long serialVersionUID = 1L;

	public CommonDomainException(String errorMessage) {
		super(errorMessage);
	}

	public CommonDomainException(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}

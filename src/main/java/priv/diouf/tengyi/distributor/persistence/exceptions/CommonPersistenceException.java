package priv.diouf.tengyi.distributor.persistence.exceptions;

import priv.diouf.tengyi.distributor.common.exceptions.CommonApplicationException;

public class CommonPersistenceException extends CommonApplicationException {
	private static final long serialVersionUID = 1L;

	public CommonPersistenceException(String errorMessage) {
		super(errorMessage);
	}

	public CommonPersistenceException(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}

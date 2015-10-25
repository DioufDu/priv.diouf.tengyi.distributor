package priv.diouf.tengyi.distributor.web.exceptions.handlers;

import javax.servlet.http.HttpServletResponse;

public abstract class GeneralExceptionHandler<TException extends Exception, TBody> implements
		ExceptionHandler<TException, TBody> {

	/*
	 * Actions
	 */

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	}
}

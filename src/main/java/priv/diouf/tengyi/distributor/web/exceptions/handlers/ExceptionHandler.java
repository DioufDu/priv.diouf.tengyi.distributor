package priv.diouf.tengyi.distributor.web.exceptions.handlers;

public interface ExceptionHandler<TException extends Exception, TBody> {

	Class<TException> getExceptionType();

	int getStatusCode();

	TBody getBody(TException ex);
}

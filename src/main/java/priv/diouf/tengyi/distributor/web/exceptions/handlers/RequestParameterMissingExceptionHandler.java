package priv.diouf.tengyi.distributor.web.exceptions.handlers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import priv.diouf.tengyi.distributor.services.exceptions.RequestParameterMissingException;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RequestParameterMissingExceptionHandler extends
		GeneralExceptionHandler<RequestParameterMissingException, String> {

	@Override
	public Class<RequestParameterMissingException> getExceptionType() {
		return RequestParameterMissingException.class;
	}

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_NOT_ACCEPTABLE;
	}

	@Override
	public String getBody(RequestParameterMissingException ex) {
		return ex.getErrorMessage();
	}
}

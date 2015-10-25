package priv.diouf.tengyi.distributor.web.exceptions.handlers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DefaultExceptionHandler extends GeneralExceptionHandler<Exception, String> {

	protected static final String DEFAULT_MESSAGE = "The system had some internal errors, please check internal log.";

	@Override
	public Class<Exception> getExceptionType() {
		return Exception.class;
	}

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_EXPECTATION_FAILED;
	}

	@Override
	public String getBody(Exception ex) {
		return DEFAULT_MESSAGE;
	}
}

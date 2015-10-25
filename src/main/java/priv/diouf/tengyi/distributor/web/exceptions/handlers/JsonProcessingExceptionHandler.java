package priv.diouf.tengyi.distributor.web.exceptions.handlers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class JsonProcessingExceptionHandler extends GeneralExceptionHandler<JsonProcessingException, String> {

	protected static final String DEFAULT_MESSAGE = "The system had some internal errors on json object progressing.";

	@Override
	public Class<JsonProcessingException> getExceptionType() {
		return JsonProcessingException.class;
	}

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_EXPECTATION_FAILED;
	}

	@Override
	public String getBody(JsonProcessingException ex) {
		return DEFAULT_MESSAGE;
	}
}

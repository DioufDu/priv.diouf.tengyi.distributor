package priv.diouf.tengyi.distributor.web.exceptions.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.ObjectError;

import priv.diouf.tengyi.distributor.web.exceptions.RequestBodyValidationException;

//@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ValidationGlobalExceptionHandler extends
GeneralExceptionHandler<RequestBodyValidationException, List<String>> {

	/*
	 * Actions
	 */

	@Override
	public Class<RequestBodyValidationException> getExceptionType() {
		return RequestBodyValidationException.class;
	}

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_NOT_ACCEPTABLE;
	}

	@Override
	public List<String> getBody(RequestBodyValidationException ex) {
		List<ObjectError> objectErrors = ex.getBindingResult().getGlobalErrors();
		ArrayList<String> bodies = new ArrayList<String>(objectErrors.size());
		for (ObjectError objectError : objectErrors) {
			bodies.add(objectError.toString());
		}
		return bodies;
	}
}

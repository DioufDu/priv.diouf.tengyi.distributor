package priv.diouf.tengyi.distributor.web.exceptions.handlers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import priv.diouf.tengyi.distributor.services.exceptions.SpecifiedEntityNotFoundException;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SpecifiedEntityNotFoundExceptionHandler extends
		GeneralExceptionHandler<SpecifiedEntityNotFoundException, String> {

	@Override
	public Class<SpecifiedEntityNotFoundException> getExceptionType() {
		return SpecifiedEntityNotFoundException.class;
	}

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_NOT_FOUND;
	}

	@Override
	public String getBody(SpecifiedEntityNotFoundException ex) {
		return ex.getErrorMessage();
	}
}

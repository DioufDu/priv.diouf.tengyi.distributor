package priv.diouf.tengyi.distributor.web.exceptions.handlers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import priv.diouf.tengyi.distributor.services.exceptions.CommonBusinessException;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CommonBusinessExceptionHandler extends GeneralExceptionHandler<CommonBusinessException, String> {

	@Override
	public Class<CommonBusinessException> getExceptionType() {
		return CommonBusinessException.class;
	}

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_EXPECTATION_FAILED;
	}

	@Override
	public String getBody(CommonBusinessException ex) {
		return ex.getErrorMessage();
	}
}

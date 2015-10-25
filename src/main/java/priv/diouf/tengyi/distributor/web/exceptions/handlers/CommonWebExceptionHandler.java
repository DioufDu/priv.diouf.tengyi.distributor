package priv.diouf.tengyi.distributor.web.exceptions.handlers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import priv.diouf.tengyi.distributor.web.exceptions.CommonWebException;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CommonWebExceptionHandler extends GeneralExceptionHandler<CommonWebException, String> {

	@Override
	public Class<CommonWebException> getExceptionType() {
		return CommonWebException.class;
	}

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_NOT_ACCEPTABLE;
	}

	@Override
	public String getBody(CommonWebException ex) {
		return ex.getErrorMessage();
	}
}

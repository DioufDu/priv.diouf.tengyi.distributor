package priv.diouf.tengyi.distributor.web.exceptions.handlers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import priv.diouf.tengyi.distributor.services.exceptions.InvalidPhotoFormatException;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class InvalidPhotoFormatExceptionHandler extends GeneralExceptionHandler<InvalidPhotoFormatException, String> {

	@Override
	public Class<InvalidPhotoFormatException> getExceptionType() {
		return InvalidPhotoFormatException.class;
	}

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_NOT_ACCEPTABLE;
	}

	@Override
	public String getBody(InvalidPhotoFormatException ex) {
		return ex.getErrorMessage();
	}
}

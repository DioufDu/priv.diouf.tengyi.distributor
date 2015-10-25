package priv.diouf.tengyi.distributor.web.exceptions.handlers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonProperty;

import priv.diouf.tengyi.distributor.web.exceptions.RequestBodyValidationException;
import priv.diouf.tengyi.distributor.web.exceptions.handlers.ValidationFieldExceptionHandler.FieldErrorBody;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ValidationFieldExceptionHandler extends
		GeneralExceptionHandler<RequestBodyValidationException, List<FieldErrorBody>> {

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
	public List<FieldErrorBody> getBody(RequestBodyValidationException ex) {
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		ArrayList<FieldErrorBody> fieldErrorBodies = new ArrayList<FieldErrorBody>(fieldErrors.size());
		for (FieldError fieldError : fieldErrors) {
			fieldErrorBodies.add(new FieldErrorBody(fieldError.getField(), fieldError.getRejectedValue(), fieldError
					.getDefaultMessage()));
		}
		return fieldErrorBodies;
	}

	protected class FieldErrorBody implements Serializable {

		/**
		 * Generated Serial Version UID
		 */
		private static final long serialVersionUID = 4486795409832623805L;

		/*
		 * Fields
		 */

		@JsonProperty("field")
		protected String field;

		@JsonProperty("value")
		protected Object rejectedValue;

		@JsonProperty("message")
		protected String defaultMessage;

		/*
		 * Constructors
		 */

		public FieldErrorBody(String field, Object rejectedValue, String defaultMessage) {
			this.field = field;
			this.rejectedValue = rejectedValue;
			this.defaultMessage = defaultMessage;
		}

		/*
		 * Additional Properties
		 */
	}
}

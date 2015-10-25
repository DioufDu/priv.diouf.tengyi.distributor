package priv.diouf.tengyi.distributor.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import priv.diouf.tengyi.distributor.web.exceptions.RequestBodyValidationException;

//@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Lazy(true)
public class BindingResultValidationInterceptor extends HandlerInterceptorAdapter implements
		CustomizedHandlerInterceptor {

	protected BindingResult bindingResult;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		for (MethodParameter methodParameter : handlerMethod.getMethodParameters()) {
			if (BindingResult.class.equals(methodParameter.getDeclaringClass())) {
				// TODO
			}
		}
		if (bindingResult != null && bindingResult.hasErrors()) {
			throw new RequestBodyValidationException(bindingResult);
		}
		return super.preHandle(request, response, handler);
	}

	/*
	 * Properties
	 */

	public BindingResult getBindingResult() {
		return bindingResult;
	}

	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}

}

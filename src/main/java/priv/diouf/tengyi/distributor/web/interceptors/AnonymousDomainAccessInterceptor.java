package priv.diouf.tengyi.distributor.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Lazy(true)
public class AnonymousDomainAccessInterceptor extends HandlerInterceptorAdapter implements CustomizedHandlerInterceptor {

	/*
	 * Fields
	 */

	private static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";

	/*
	 * Actions
	 */

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
		return super.preHandle(request, response, handler);
	}
}

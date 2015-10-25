package priv.diouf.tengyi.distributor.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import priv.diouf.tengyi.distributor.web.annontations.AuthenticatedRole;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Lazy(true)
public class RoleBasedAuthenticationInterceptor extends HandlerInterceptorAdapter implements CustomizedHandlerInterceptor {

	/*
	 * Fields
	 */

	private static final String ERROR_MSG = "You don't have authentication to access this page.";

	/*
	 * Actions
	 */

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		AuthenticatedRole role = ((HandlerMethod) handler).getMethodAnnotation(AuthenticatedRole.class);
		if (role != null && ArrayUtils.isNotEmpty(role.value())) {
			for (String roleName : role.value()) {
				if (request.isUserInRole(roleName)) {
					return super.preHandle(request, response, handler);
				}
			}
		} else {
			return super.preHandle(request, response, handler);
		}
		response.sendError(403);
		response.getWriter().println(ERROR_MSG);
		return false;
	}
}

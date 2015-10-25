package priv.diouf.tengyi.distributor.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import priv.diouf.tengyi.distributor.web.annontations.AuthenticatedPrivilege;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Lazy(true)
public class PrivilegeBasedAuthenticationInterceptor extends HandlerInterceptorAdapter implements
		CustomizedHandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		AuthenticatedPrivilege privilege = ((HandlerMethod) handler).getMethodAnnotation(AuthenticatedPrivilege.class);
		if (privilege != null && ArrayUtils.isNotEmpty(privilege.value())) {
			if (request.getUserPrincipal() != null) {
				for (String privilegeName : privilege.value()) {
					if (StringUtils.equalsIgnoreCase(privilegeName, request.getUserPrincipal().getName())) {
						return super.preHandle(request, response, handler);
					}
				}
			}
		} else {
			return super.preHandle(request, response, handler);
		}
		response.sendError(403);
		return false;
	}
}

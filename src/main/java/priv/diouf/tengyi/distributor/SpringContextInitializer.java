package priv.diouf.tengyi.distributor;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@ComponentScan
public class SpringContextInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/*
	 * Fields
	 */

	/*
	 * Configurations
	 */

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringContextInitializer.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringContextInitializer.class };
	}

	/*
	 * Servlet Mappings
	 */

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/api/*" };
	}

	@Override
	protected void registerContextLoaderListener(ServletContext servletContext) {
		servletContext.addListener(RequestContextListener.class);
		super.registerContextLoaderListener(servletContext);
	}

}
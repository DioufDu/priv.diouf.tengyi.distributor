package priv.diouf.tengyi.distributor;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	/*
	 * Fields
	 */

	public static final String ROOT_NAMESPACE = "priv.diouf.tengyi.distributor";

	/*
	 * Root Config
	 */

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringAppConfig.class };
	}

	@Configuration
	public static class SpringAppConfig {
	}

	/*
	 * Servlet Config
	 */

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringWebConfig.class };
	}

	@Configuration
	@ComponentScan(basePackages = SpringConfig.ROOT_NAMESPACE, scopedProxy = ScopedProxyMode.TARGET_CLASS)
	@EnableAsync
	@EnableWebMvc
	@EnableTransactionManagement
	@EnableJpaRepositories(transactionManagerRef = "annotationDrivenTransactionManager")
	public static class SpringWebConfig extends WebMvcConfigurerAdapter {

	}

	/*
	 * Servlet Mappings
	 */

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/api/*" };
	}
}
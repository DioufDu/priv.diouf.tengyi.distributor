package priv.diouf.tengyi.distributor.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import priv.diouf.tengyi.distributor.web.interceptors.CustomizedHandlerInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	/*
	 * Fields
	 */

	private static final int MAX_UPLOAD_SIZE = 4 * 1024 * 1024;

	@Autowired(required = false)
	protected List<CustomizedHandlerInterceptor> customizedHandlerInterceptor;

	/*
	 * Actions
	 */

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		if (customizedHandlerInterceptor != null) {
			for (CustomizedHandlerInterceptor customizedHandlerInterceptor : customizedHandlerInterceptor) {
				registry.addInterceptor(customizedHandlerInterceptor);
			}
		}
	}

	/*
	 * Beans
	 */

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	@Lazy(true)
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxUploadSize(MAX_UPLOAD_SIZE);
		return commonsMultipartResolver;
	}
}

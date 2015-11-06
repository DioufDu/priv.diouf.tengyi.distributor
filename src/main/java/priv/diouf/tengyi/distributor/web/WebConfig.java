package priv.diouf.tengyi.distributor.web;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.persistence.repositories.account.AccountRepository;
import priv.diouf.tengyi.distributor.web.interceptors.CustomizedHandlerInterceptor;

@Configuration
@EnableAsync
@EnableWebMvc
@EnableWebSecurity
@EnableTransactionManagement
@EnableJpaRepositories(transactionManagerRef = "annotationDrivenTransactionManager")
public class WebConfig extends WebMvcConfigurerAdapter {

	/*
	 * Fields
	 */

	private static final ConcurrentHashMap<String, Account> LOGIN_USER_CACHE = new ConcurrentHashMap<String, Account>(128);

	private static final int MAX_UPLOAD_SIZE = 4 * 1024 * 1024;

	@Autowired
	protected AccountRepository accountRepository;

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
	@Lazy(false)
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxUploadSize(MAX_UPLOAD_SIZE);
		return commonsMultipartResolver;
	}

	@Bean(name = "loginUser")
	@Scope(value = WebApplicationContext.SCOPE_REQUEST)
	@Lazy(true)
	@Autowired
	public Account loginUser(HttpServletRequest request) {
		if (StringUtils.isBlank(request.getRemoteUser())) {
			return null;
		}
		Account loginUser = LOGIN_USER_CACHE.get(request.getRemoteUser());
		if (loginUser != null) {
			return loginUser;
		}
		loginUser = accountRepository.findOneByToken(request.getRemoteUser());
		LOGIN_USER_CACHE.put(request.getRemoteUser(), loginUser);
		return loginUser;
	}
}

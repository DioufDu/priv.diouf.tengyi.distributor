package priv.diouf.tengyi.distributor.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

@Configuration
@Order(1)
public class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

	/*
	 * Fields
	 */

	/*
	 * Actions
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// RESTful APIs
				.antMatcher("/api/**").authorizeRequests()
				// RESTful APIs - AccessDecisionManager
				.accessDecisionManager(getAccessDecisionManager()).expressionHandler(this.getWebSecurityExpressionHandler())
				// RESTful APIs - Authenticated Required
				.anyRequest().authenticated()
				// Login - Basic
				.and().httpBasic();
	}

	/*
	 * Private & Protected Methods
	 */

	private AccessDecisionManager getAccessDecisionManager() {
		List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<AccessDecisionVoter<?>>();
		decisionVoters.add(new RoleVoter());
		decisionVoters.add(new AuthenticatedVoter());
		WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
		webExpressionVoter.setExpressionHandler(this.getWebSecurityExpressionHandler());
		decisionVoters.add(webExpressionVoter);
		return new AffirmativeBased(decisionVoters);
	}

	private DefaultWebSecurityExpressionHandler getWebSecurityExpressionHandler() {
		return new DefaultWebSecurityExpressionHandler();
	}
}

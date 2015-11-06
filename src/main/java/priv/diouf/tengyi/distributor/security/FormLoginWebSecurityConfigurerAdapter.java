package priv.diouf.tengyi.distributor.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.services.AccountQueryService;

@Configuration
public class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	/*
	 * Fields
	 */

	@Autowired
	private AccountQueryService accountQueryService;

	/*
	 * Actions
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Login
		http.authorizeRequests().anyRequest().authenticated()
				// Login - Form
				.and().csrf().disable().formLogin()
				// Login Page
				.loginPage("/authentication.html#/login")
				// Login Page from authentication failure
				.failureUrl("/authentication.html#/fail")
				// Login API
				.loginProcessingUrl("/api/authentication/login")
				// Login Form Parameters
				.usernameParameter("username").passwordParameter("password").permitAll();

		// Logout
		http.logout()
				// Logout Url
				.logoutUrl("/logout")
				// Logout Success Page
				.logoutSuccessUrl("/authentication.html#/logout");

		// Session management
		http.sessionManagement().sessionFixation().none();

		// Remember Me
		http.rememberMe().tokenValiditySeconds(15 * 60 * 1000);

		// Exception Handling
		http.exceptionHandling().accessDeniedPage("/forbidden.html");

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Account account = accountQueryService.findOneByToken(username);
				return new User(account.getName(), "", account.getType().getAuthorities());
			}
		});// .passwordEncoder(new Md5PasswordEncoder());
		auth.eraseCredentials(false);
	}

	/*
	 * Private & Protected Methods
	 */

}

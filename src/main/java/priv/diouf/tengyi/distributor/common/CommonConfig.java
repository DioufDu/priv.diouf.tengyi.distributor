package priv.diouf.tengyi.distributor.common;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.Base64Encoder;

@Configuration
public class CommonConfig {

	/*
	 * Fields
	 */

	public static final XStream XSTREAM = new XStream();
	public static final Base64Encoder BASE64_ENCODER = new Base64Encoder();

	/*
	 * Bean Factories
	 */

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public synchronized InitialContext initialContext() throws NamingException {
		return new InitialContext();
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	@Lazy(true)
	public synchronized XStream xStream() {
		return XSTREAM;
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	@Lazy(true)
	public synchronized Base64Encoder base64Encoder() {
		return BASE64_ENCODER;
	}
}

package priv.diouf.tengyi.distributor.persistence;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Lazy(false)
public class PersistenceConfigNotifier {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceConfigNotifier.class);

	@Autowired
	protected PersistenceConfig persistenceConfig;

	@PostConstruct
	public void notifyAllChecks() {
		LOGGER.debug(this.checkEntityManagerFactory());
		LOGGER.debug(this.checkEntityManager());
	}

	public String checkEntityManager() {
		String result = "Entity Manager is " + (persistenceConfig.entityManager() == null ? " not " : "") + "ready.";
		System.out.println(result);
		return result;
	}

	public String checkEntityManagerFactory() {
		String result = "Entity Manager Factory is " + (persistenceConfig.entityManagerFactory() == null ? " not " : "") + "ready.";
		System.out.println(result);
		return result;
	}
}

package priv.diouf.tengyi.distributor.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.SharedEntityManagerCreator;
import org.springframework.transaction.PlatformTransactionManager;

import priv.diouf.tengyi.distributor.persistence.exceptions.CommonPersistenceException;

@Configuration
public class PersistenceConfig implements AutoCloseable {

	/*
	 * Fields
	 */

	// The persistence schema name would determined by deployment environment
	public static final String PERSISTENCE_SCHEMA_NAME = "";

	// The persistence unit name configured within persistence.xml file
	public static final String PERSISTENCE_UNIT_NAME = "priv.diouf.tengyi.distributor";

	/*
	 * Wired Beans
	 */

	private EntityManagerFactory entityManagerFactory;

	private EntityManager entityManager;

	private PlatformTransactionManager annotationDrivenTransactionManager;

	/*
	 * Bean Factories
	 */

	@Bean(autowire = Autowire.BY_TYPE)
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	@Lazy(false)
	public synchronized EntityManagerFactory entityManagerFactory() throws CommonPersistenceException {
		if (this.entityManagerFactory == null) {
			try {
				this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			} catch (Exception ex) {
				throw new CommonPersistenceException("Other unexcepted exception raised on creating entity manager factory.", ex);
			}
		}
		return this.entityManagerFactory;
	}

	@Bean(autowire = Autowire.BY_TYPE)
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	@Lazy(false)
	public synchronized PlatformTransactionManager annotationDrivenTransactionManager() {
		if (this.annotationDrivenTransactionManager == null) {
			this.annotationDrivenTransactionManager = new JpaTransactionManager(this.entityManagerFactory());
		}
		return this.annotationDrivenTransactionManager;
	}

	@Bean(autowire = Autowire.BY_TYPE)
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	@Primary
	@Lazy(false)
	public synchronized EntityManager entityManager() {
		if (this.entityManager == null) {
			this.entityManager = SharedEntityManagerCreator.createSharedEntityManager(this.entityManagerFactory());
		}
		return this.entityManager;
	}

	@Override
	public void close() throws Exception {
		this.entityManager.close();
		this.entityManagerFactory.close();
	}

	/*
	 * Private & Protected Methods
	 */

}

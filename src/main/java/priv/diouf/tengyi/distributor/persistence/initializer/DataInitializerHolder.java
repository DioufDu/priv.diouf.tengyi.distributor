package priv.diouf.tengyi.distributor.persistence.initializer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import priv.diouf.tengyi.distributor.persistence.initializer.factories.EntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.MasterDataEntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.TransactionalDataEntityFactory;

@SuppressWarnings("rawtypes")
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Lazy(false)
public class DataInitializerHolder {

	/**
	 * Fields
	 */

	protected static final DataInitialiStrategy STRATEGY = DataInitialiStrategy.APPEND;

	@Autowired
	protected EntityManager entityManager;

	@Autowired(required = false)
	protected List<EntityFactory> entityFactories;

	@Autowired(required = false)
	protected List<MasterDataEntityFactory> masterDataEntityFactories;

	@Autowired(required = false)
	protected List<TransactionalDataEntityFactory> transactionalDataEntityFactories;

	/**
	 * Properties
	 */

	// @PostConstruct
	public List<EntityFactory> execute() {
		switch (STRATEGY) {
		case APPEND:
			for (EntityFactory entityFactory : entityFactories) {
				entityFactory.saveAll();
			}
			break;
		case APPEND_WHILE_EMPTY:
			ArrayList<EntityFactory> availableEntityFactories = new ArrayList<EntityFactory>(entityFactories.size());
			for (EntityFactory entityFactory : entityFactories) {
				if (!entityFactory.isDataExisted()) {
					availableEntityFactories.add(entityFactory);
				}
			}
			for (EntityFactory entityFactory : availableEntityFactories) {
				entityFactory.saveAll();
			}
			break;
		case MASTER_DATA_ONLY: {
			for (EntityFactory entityFactory : masterDataEntityFactories) {
				if (!entityFactory.isDataExisted()) {
					entityFactory.saveAll();
				}
			}
		}
			break;
		case CLEAR_ALL:
			// TODO: This function should be disabled deal to the db fk constraint
			for (EntityFactory entityFactory : entityFactories) {
				entityFactory.clearAll();
			}
			break;
		case CLEAR_AND_APPEND:
			// TODO: This function should be disabled deal to the db fk constraint
			for (EntityFactory entityFactory : entityFactories) {
				entityFactory.clearAll();
			}
			for (EntityFactory entityFactory : entityFactories) {
				entityFactory.saveAll();
			}
			break;
		case NEVER:
		default:
			// DO NOTHING
		}
		for (EntityFactory entityFactory : entityFactories) {
			entityFactory.releaseReferences();
		}
		entityManager.clear();
		return this.entityFactories;
	}

	protected boolean isDataExisted() {
		for (EntityFactory entityFactory : entityFactories) {
			if (entityFactory.isDataExisted()) {
				return true;
			}
		}
		return false;
	}

	protected void saveAll() {
		for (EntityFactory entityFactory : entityFactories) {
			entityFactory.saveAll();
		}
	}

	protected void clearAll() {
		for (EntityFactory entityFactory : entityFactories) {
			entityFactory.clearAll();
		}
	}

}

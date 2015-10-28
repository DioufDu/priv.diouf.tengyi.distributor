package priv.diouf.tengyi.distributor.persistence.initializer.factories;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

public abstract class GeneralEntityFactory<TEntity> implements EntityFactory<TEntity> {

	/*
	 * Fields
	 */

	protected static final Date SYSTEM_START_TIME = Calendar.getInstance().getTime();
	private Type[] genericTypes;
	private Class<TEntity> entityType;
	private List<TEntity> allCreatedEntities;
	private boolean isSaved = false;

	/**
	 * Data Initializer
	 */

	protected abstract JpaRepository<TEntity, ?> getGeneralJpaRepository();

	protected abstract EntityFactory<?>[] getDependEntityFactories();

	protected abstract void fulfill(List<TEntity> allCreatedEntities);

	@Override
	@Transactional(readOnly = true)
	public synchronized boolean isDataExisted() {
		return this.getGeneralJpaRepository().count() > 0;
	}

	@Override
	@Transactional
	public void saveAll() {
		if (isSaved) {
			return;
		}
		this.getGeneralJpaRepository().save(this.getAll());
		this.getGeneralJpaRepository().flush();
		this.isSaved = true;
	}

	@Override
	@Transactional
	public synchronized void clearAll() {
		this.getGeneralJpaRepository().deleteAllInBatch();
		this.getGeneralJpaRepository().flush();
	}

	@Override
	public synchronized List<TEntity> getAll() {
		// Return entities once created
		if (!CollectionUtils.isEmpty(allCreatedEntities)) {
			return allCreatedEntities;
		}
		// Resolve&Create the depend entities before creation
		EntityFactory<?>[] dependEntityFactories = getDependEntityFactories();
		if (!ArrayUtils.isEmpty(dependEntityFactories)) {
			for (EntityFactory<?> dependEntityFactory : dependEntityFactories) {
				dependEntityFactory.saveAll();
			}
		}
		// Fulfill entities and scope with current entity only
		allCreatedEntities = new ArrayList<TEntity>();
		this.fulfill(allCreatedEntities);
		if (!CollectionUtils.isEmpty(allCreatedEntities)) {
			allCreatedEntities = this.getGeneralJpaRepository().save(allCreatedEntities);
		}
		this.getGeneralJpaRepository().flush();
		return allCreatedEntities;
	}

	@Override
	public void releaseReferences() {
		this.allCreatedEntities = null;
	}

	/*
	 * Private & Protected Methods
	 */

	/**
	 * Get the actual generic types from this general repository
	 *
	 * @return
	 */
	private Type[] getGenericTypes() {
		// TODO: Refactor with perfect lock
		if (this.genericTypes == null) {
			synchronized (this) {
				this.genericTypes = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
			}
		}
		return this.genericTypes;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Class<TEntity> getEntityType() {
		if (this.entityType == null) {
			this.entityType = (Class<TEntity>) this.getGenericTypes()[0];
		}
		return this.entityType;
	}
}

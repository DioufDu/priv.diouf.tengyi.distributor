package priv.diouf.tengyi.distributor.persistence.initializer.factories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EntityFactory<TEntity> {

	Class<TEntity> getEntityType();

	void releaseReferences();

	@Transactional(readOnly = true)
	boolean isDataExisted();

	@Transactional
	List<TEntity> getAll();

	@Transactional
	void saveAll();

	@Transactional
	void clearAll();

}

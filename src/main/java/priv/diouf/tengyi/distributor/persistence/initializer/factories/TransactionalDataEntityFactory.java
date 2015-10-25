package priv.diouf.tengyi.distributor.persistence.initializer.factories;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TransactionalDataEntityFactory<TEntity> extends EntityFactory<TEntity> {

}

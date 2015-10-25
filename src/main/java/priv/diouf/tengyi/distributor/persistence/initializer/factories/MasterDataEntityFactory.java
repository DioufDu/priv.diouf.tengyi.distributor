package priv.diouf.tengyi.distributor.persistence.initializer.factories;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MasterDataEntityFactory<TEntity> extends EntityFactory<TEntity> {

}

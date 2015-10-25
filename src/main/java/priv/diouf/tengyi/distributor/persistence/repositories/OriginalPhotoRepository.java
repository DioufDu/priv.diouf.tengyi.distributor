package priv.diouf.tengyi.distributor.persistence.repositories;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.OriginalPhoto;
import priv.diouf.tengyi.distributor.persistence.models.OriginalPhoto_;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class OriginalPhotoRepository extends GeneralPhotoRepository<OriginalPhoto, OriginalPhoto_> {

	/*
	 * Constructors
	 */

	@Autowired
	public OriginalPhotoRepository(EntityManager entityManager) {
		super(OriginalPhoto.class, entityManager);
	}
}

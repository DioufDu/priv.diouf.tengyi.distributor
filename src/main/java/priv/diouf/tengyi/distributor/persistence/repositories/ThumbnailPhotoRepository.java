package priv.diouf.tengyi.distributor.persistence.repositories;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.ThumbnailPhoto;
import priv.diouf.tengyi.distributor.persistence.models.ThumbnailPhoto_;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ThumbnailPhotoRepository extends GeneralPhotoRepository<ThumbnailPhoto, ThumbnailPhoto_> {

	/*
	 * Constructors
	 */

	@Autowired
	public ThumbnailPhotoRepository(EntityManager entityManager) {
		super(ThumbnailPhoto.class, entityManager);
	}
}

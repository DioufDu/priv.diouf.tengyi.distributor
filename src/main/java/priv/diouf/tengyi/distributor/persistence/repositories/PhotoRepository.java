package priv.diouf.tengyi.distributor.persistence.repositories;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.Photo;
import priv.diouf.tengyi.distributor.persistence.models.Photo_;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class PhotoRepository extends GeneralPhotoRepository<Photo, Photo_> {

	/*
	 * Constructors
	 */

	@Autowired
	public PhotoRepository(EntityManager entityManager) {
		super(Photo.class, entityManager);
	}
}

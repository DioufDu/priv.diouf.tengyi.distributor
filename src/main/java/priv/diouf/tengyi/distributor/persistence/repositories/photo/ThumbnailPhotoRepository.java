package priv.diouf.tengyi.distributor.persistence.repositories.photo;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.photo.ThumbnailPhoto;
import priv.diouf.tengyi.distributor.persistence.models.photo.ThumbnailPhoto_;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
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

package priv.diouf.tengyi.distributor.persistence.repositories.photo;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.photo.Photo;
import priv.diouf.tengyi.distributor.persistence.models.photo.Photo_;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
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

package priv.diouf.tengyi.distributor.persistence.repositories.photo;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.photo.StandardPhoto;
import priv.diouf.tengyi.distributor.persistence.models.photo.StandardPhoto_;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Transactional
public class StandardPhotoRepository extends GeneralPhotoRepository<StandardPhoto, StandardPhoto_> {

	/*
	 * Constructors
	 */

	@Autowired
	public StandardPhotoRepository(EntityManager entityManager) {
		super(StandardPhoto.class, entityManager);
	}
}

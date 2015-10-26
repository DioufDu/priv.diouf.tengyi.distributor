package priv.diouf.tengyi.distributor.persistence.repositories.photo;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.photo.OriginalPhoto;
import priv.diouf.tengyi.distributor.persistence.models.photo.OriginalPhoto_;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
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

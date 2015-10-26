package priv.diouf.tengyi.distributor.persistence.repositories.account;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.photo.AvatarPhotoGroup;
import priv.diouf.tengyi.distributor.persistence.models.photo.AvatarPhotoGroup_;
import priv.diouf.tengyi.distributor.persistence.repositories.photo.GeneralPhotoGroupRepository;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Transactional
public class AvatarPhotoGroupRepository extends GeneralPhotoGroupRepository<AvatarPhotoGroup, AvatarPhotoGroup_> {

	/*
	 * Constructors
	 */

	@Autowired
	public AvatarPhotoGroupRepository(EntityManager entityManager) {
		super(AvatarPhotoGroup.class, entityManager);
	}
}

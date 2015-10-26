package priv.diouf.tengyi.distributor.persistence.repositories.photo;

import javax.persistence.EntityManager;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.photo.PhotoGroup;
import priv.diouf.tengyi.distributor.persistence.models.photo.PhotoGroup_;
import priv.diouf.tengyi.distributor.persistence.repositories.GeneralJpaRepository;

@NoRepositoryBean
@Transactional
public class GeneralPhotoGroupRepository<TPhotoGroup extends PhotoGroup, TPhotoGroup_ extends PhotoGroup_> extends GeneralJpaRepository<TPhotoGroup, TPhotoGroup_, Long> {

	/*
	 * Constructors
	 */

	public GeneralPhotoGroupRepository(Class<TPhotoGroup> PhotoGroupClass, EntityManager entityManager) {
		super(PhotoGroupClass, entityManager);
	}

	/*
	 * Actions
	 */
}

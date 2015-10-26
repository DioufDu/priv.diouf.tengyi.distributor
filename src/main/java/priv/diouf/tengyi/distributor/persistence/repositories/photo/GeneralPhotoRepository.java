package priv.diouf.tengyi.distributor.persistence.repositories.photo;

import javax.persistence.EntityManager;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.photo.Photo;
import priv.diouf.tengyi.distributor.persistence.models.photo.Photo_;
import priv.diouf.tengyi.distributor.persistence.repositories.GeneralJpaRepository;

@NoRepositoryBean
@Transactional
public class GeneralPhotoRepository<TPhoto extends Photo, TPhoto_ extends Photo_> extends GeneralJpaRepository<TPhoto, TPhoto_, Long> {

	/*
	 * Constructors
	 */

	public GeneralPhotoRepository(Class<TPhoto> photoClass, EntityManager entityManager) {
		super(photoClass, entityManager);
	}

	/*
	 * Actions
	 */
}

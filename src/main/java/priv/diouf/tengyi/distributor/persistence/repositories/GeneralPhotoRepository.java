package priv.diouf.tengyi.distributor.persistence.repositories;

import javax.persistence.EntityManager;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.Photo;
import priv.diouf.tengyi.distributor.persistence.models.Photo_;

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

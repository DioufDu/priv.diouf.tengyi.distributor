package priv.diouf.tengyi.distributor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.Photo;
import priv.diouf.tengyi.distributor.persistence.repositories.FullScreenPhotoRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.OriginalPhotoRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.PhotoRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.ThumbnailPhotoRepository;
import priv.diouf.tengyi.distributor.services.exceptions.SpecifiedEntityNotFoundException;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PhotoQueryService {

	/*
	 * Fields
	 */

	@Autowired
	protected PhotoRepository photoRepository;

	@Autowired
	protected ThumbnailPhotoRepository thumbnailPhotoRepository;

	@Autowired
	protected FullScreenPhotoRepository fullSizePhotoRepository;

	@Autowired
	protected OriginalPhotoRepository originalPhotoRepository;

	/*
	 * Queries
	 */

	@Transactional(readOnly = true)
	public Photo findPhoto(Long id) {
		if (0L == id) {
			throw new SpecifiedEntityNotFoundException("dish");
		}
		Photo photo = photoRepository.findOne(id);
		if (null == photo) {
			throw new SpecifiedEntityNotFoundException("dish");
		}
		return photo;
	}

	/*
	 * Private & Protected Methods
	 */

}

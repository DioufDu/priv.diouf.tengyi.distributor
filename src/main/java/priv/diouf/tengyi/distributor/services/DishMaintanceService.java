package priv.diouf.tengyi.distributor.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.Dish;
import priv.diouf.tengyi.distributor.persistence.models.RateGroup;
import priv.diouf.tengyi.distributor.persistence.repositories.DishRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.PhotoRepository;
import priv.diouf.tengyi.distributor.services.exceptions.SpecifiedEntityNotFoundException;
import priv.diouf.tengyi.distributor.web.models.requests.dish.CreationRequest;
import priv.diouf.tengyi.distributor.web.models.requests.dish.UpdateRequest;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class DishMaintanceService {

	/*
	 * Fields
	 */

	@Autowired
	protected DishRepository dishRepository;

	@Autowired
	protected PhotoRepository photoRepository;

	@Autowired
	protected PhotoMaintanceService photoMaintanceService;

	/*
	 * CUD Actions
	 */

	@Transactional
	public Dish create(CreationRequest request) {
		// Validations
		if (request == null) {
			return null;
		}
		// Create
		Dish dish = migrateScalarProperties(request, new Dish());
		dish.setRateGroup(new RateGroup(dish));
		if (request.getPhoto() != null) {
			photoMaintanceService.migratePhotos(request.getPhoto(), dish);
		}
		return dishRepository.saveAndFlush(dish);
	}

	@Transactional
	public Dish update(long dishId, UpdateRequest request) {
		// Validations
		if (0L == dishId) {
			throw new SpecifiedEntityNotFoundException("dish");
		}
		Dish dish = dishRepository.findOne(dishId);
		if (dish == null) {
			throw new SpecifiedEntityNotFoundException("dish");
		}
		// Update
		migrateScalarProperties(request, dish);
		dish.getModification().setLastModifiedDateTime(Calendar.getInstance());
		if (request.isPhotoRenewRequired() || (request.getPhoto() != null && request.getPhoto().getAngle() % 360 != 0)) {
			photoMaintanceService.migratePhotos(request.getPhoto(), dish);
		}
		return dishRepository.saveAndFlush(dish);
	}

	@Transactional
	public void delete(long dishId) {
		if (0L == dishId) {
			throw new SpecifiedEntityNotFoundException("dish");
		}
		if (!dishRepository.exists(dishId)) {
			throw new SpecifiedEntityNotFoundException("dish");
		}
		dishRepository.delete(dishId);
	}

	/*
	 * Private & Protected Methods
	 */

	private Dish migrateScalarProperties(CreationRequest request, Dish dish) {
		dish.setChineseName(request.getChineseName());
		dish.setEnglishName(request.getEnglishName());
		dish.setLine(request.getLine());
		dish.setDescription(request.getDescription());
		dish.setOfferDate(request.getOfferDate());
		dish.setRecommended(request.isRecommended());
		dish.setRecommendedReason(request.getRecommendedReason());

		return dish;
	}
}

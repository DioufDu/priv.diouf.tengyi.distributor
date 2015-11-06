package priv.diouf.tengyi.distributor.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.Modification;
import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.persistence.models.photo.ProductPhotoGroup;
import priv.diouf.tengyi.distributor.persistence.models.product.PricingModel;
import priv.diouf.tengyi.distributor.persistence.models.product.Product;
import priv.diouf.tengyi.distributor.persistence.repositories.photo.FullScreenPhotoRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.photo.OriginalPhotoRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.photo.StandardPhotoRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.photo.ThumbnailPhotoRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.product.ProductPhotoGroupRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.product.ProductRepository;
import priv.diouf.tengyi.distributor.services.exceptions.SpecifiedEntityNotFoundException;
import priv.diouf.tengyi.distributor.web.models.requests.photo.PhotoGroupMergeRequest;
import priv.diouf.tengyi.distributor.web.models.requests.product.PricingModelMergeRequest;
import priv.diouf.tengyi.distributor.web.models.requests.product.ProductCreationRequest;
import priv.diouf.tengyi.distributor.web.models.requests.product.ProductUpdateRequest;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ProductMaintanceService {

	/*
	 * Fields
	 */

	@Autowired
	protected ProductRepository productRepository;

	@Autowired
	protected ProductPhotoGroupRepository productPhotoGroupRepository;

	@Autowired
	protected ThumbnailPhotoRepository thumbnailPhotoRepository;

	@Autowired
	protected StandardPhotoRepository standardPhotoRepository;

	@Autowired
	protected FullScreenPhotoRepository fullScreenPhotoRepository;

	@Autowired
	protected OriginalPhotoRepository originalPhotoRepository;

	/*
	 * CUD Actions
	 */

	public Product create(ProductCreationRequest request, Account account) {
		Product product = new Product();
		// Scalar Properties
		this.migrateScalarProperties(request, product);
		// Navigations - Pricing Model
		product.setPricingModel(this.migratePricingModel(request.getPricingModel(), product.getPricingModel()));
		// Navigations - Modification
		product.setModification(this.migrateModification(new Modification(account), account));
		// Navigations - Photo
		product.setProductPhotoGroup(this.migratePhoto(request.getPhoto(), new ProductPhotoGroup(product), account));
		// Return
		return product;
	}

	public Product update(long productId, ProductUpdateRequest request, Account account) {
		// Validation
		if (productId < 1) {
			throw new SpecifiedEntityNotFoundException("product");
		}
		Product product = productRepository.findOneWithAllDetails(productId);
		if (product == null) {
			throw new SpecifiedEntityNotFoundException("product");
		}
		// Scalar Properties
		this.migrateScalarProperties(request, product);
		// Navigations - Pricing Model
		product.setPricingModel(this.migratePricingModel(request.getPricingModel(), product.getPricingModel()));
		// Navigations - Modification
		product.setModification(this.migrateModification(new Modification(account), account));
		// Navigations - Photo
		product.setProductPhotoGroup(this.migratePhoto(request.getPhoto(), new ProductPhotoGroup(product), account));
		// Return
		return product;
	}

	public void delete(long productId) {
		// Validation
		if (productId < 1 || !productRepository.exists(productId)) {
			throw new SpecifiedEntityNotFoundException("product");
		}
		// Delete
		productRepository.delete(productId);
	}

	/*
	 * Private & Protected Methods
	 */

	protected Product migrateScalarProperties(ProductCreationRequest request, Product product) {
		// Scalar Fields
		product.setName(request.getName());
		product.setModel(request.getModel());
		product.setSpecification(request.getSpecification());
		product.setSerie(request.getSerie());
		product.setComment(request.getComment());
		product.setStatus(request.getStatus());
		// Return
		return product;
	}

	/*
	 * Private & Protected Methods
	 */

	private PricingModel migratePricingModel(PricingModelMergeRequest request, PricingModel pricingModel) {
		if (request == null) {
			return pricingModel;
		}
		if (pricingModel == null) {
			pricingModel = new PricingModel();
		}
		// Navigations - Pricing Model
		pricingModel.setFactoryPrice(request.getFactoryPrice());
		pricingModel.setStorePrice(request.getStorePrice());
		pricingModel.setTerminalPrice(request.getTerminalPrice());
		pricingModel.setUnifiedPrice(request.getUnifiedPrice());
		pricingModel.setStrategy(request.getStrategy());

		// Return
		return pricingModel;
	}

	private Modification migrateModification(Modification modification, Account account) {
		if (modification == null) {
			return new Modification(account);
		}
		// Navigations - Modification
		if (modification.getCreateBy() == null && account != null) {
			modification.setCreateBy(account);
		}
		if (modification.getCreateOn() == null) {
			modification.setCreateOn(Calendar.getInstance());
		}
		if (account != null) {
			modification.setUpdateBy(account);
		}
		modification.setUpdateOn(Calendar.getInstance());
		// Return
		return modification;
	}

	private ProductPhotoGroup migratePhoto(PhotoGroupMergeRequest request, ProductPhotoGroup productPhotoGroup, Account account) {
		if (request == null) {
			return productPhotoGroup;
		}
		if (productPhotoGroup == null) {
			productPhotoGroup = new ProductPhotoGroup();
		}
		// Navigations - Photo
		if (request.getThumbnailPhotoId() != null && !request.getThumbnailPhotoId().equals(productPhotoGroup.getThumbnailPhoto().getId())) {
			productPhotoGroup.setThumbnailPhoto(thumbnailPhotoRepository.findOne(request.getThumbnailPhotoId()));
		}
		if (request.getStandardPhotoId() != null && !request.getStandardPhotoId().equals(productPhotoGroup.getStandardPhoto().getId())) {
			productPhotoGroup.setStandardPhoto(standardPhotoRepository.findOne(request.getStandardPhotoId()));
		}
		if (request.getFullScreenPhotoId() != null && !request.getFullScreenPhotoId().equals(productPhotoGroup.getFullScreenPhoto()
				.getId())) {
			productPhotoGroup.setFullScreenPhoto(fullScreenPhotoRepository.findOne(request.getFullScreenPhotoId()));
		}
		if (request.getOriginalPhotoId() != null && !request.getOriginalPhotoId().equals(productPhotoGroup.getOriginalPhoto().getId())) {
			productPhotoGroup.setOriginalPhoto(originalPhotoRepository.findOne(request.getOriginalPhotoId()));
		}
		// Navigations - Modification
		if (productPhotoGroup.getModification() == null) {
			productPhotoGroup.setModification(new Modification(account));
		}
		this.migrateModification(productPhotoGroup.getModification(), account);
		// Return
		return productPhotoGroup;
	}
}

package priv.diouf.tengyi.distributor.persistence.repositories.product;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.photo.ProductPhotoGroup;
import priv.diouf.tengyi.distributor.persistence.models.photo.ProductPhotoGroup_;
import priv.diouf.tengyi.distributor.persistence.repositories.photo.GeneralPhotoGroupRepository;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Transactional
public class ProductPhotoGroupRepository extends GeneralPhotoGroupRepository<ProductPhotoGroup, ProductPhotoGroup_> {

	/*
	 * Constructors
	 */

	@Autowired
	public ProductPhotoGroupRepository(EntityManager entityManager) {
		super(ProductPhotoGroup.class, entityManager);
	}
}

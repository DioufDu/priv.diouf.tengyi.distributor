package priv.diouf.tengyi.distributor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.product.Product;
import priv.diouf.tengyi.distributor.persistence.repositories.product.ProductRepository;
import priv.diouf.tengyi.distributor.web.models.requests.account.CreationRequest;
import priv.diouf.tengyi.distributor.web.models.requests.account.UpdateRequest;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ProductMaintanceService {

	/*
	 * Fields
	 */

	@Autowired
	protected ProductRepository productRepository;

	/*
	 * CUD Actions
	 */

	@Transactional
	public Product create(CreationRequest request) {
		// TODO
		return null;
	}

	@Transactional
	public Product update(long productId, UpdateRequest request) {
		// TODO
		return null;
	}

	@Transactional
	public void delete(long productId) {
		// TODO
	}

	/*
	 * Private & Protected Methods
	 */

	protected Product migrateScalarProperties(CreationRequest request, Product product) {
		// TODO
		return null;
	}
}

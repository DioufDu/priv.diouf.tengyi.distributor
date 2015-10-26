package priv.diouf.tengyi.distributor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.product.Product;
import priv.diouf.tengyi.distributor.persistence.repositories.product.ProductRepository;
import priv.diouf.tengyi.distributor.services.exceptions.SpecifiedEntityNotFoundException;
import priv.diouf.tengyi.distributor.services.models.AdvancedSearchCriteria;
import priv.diouf.tengyi.distributor.services.models.BasicSearchCriteria;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ProductQueryService {

	/*
	 * Fields
	 */

	protected static final String DEFAULT_SORT_PROPERTIES = "id";

	@Autowired
	protected ProductRepository productRepository;

	/*
	 * Queries
	 */

	@Transactional(readOnly = true)
	public Page<Product> findAll(AdvancedSearchCriteria criteria, PageRequest pageRequest) {
		// TODO
		return null;// productRepository.query(criteria, pageRequest);
	}

	@Transactional(readOnly = true)
	public Page<Product> findAll(BasicSearchCriteria criteria, PageRequest pageRequest) {
		// TODO
		return null;// productRepository.query(criteria, pageRequest);
	}

	@Transactional(readOnly = true)
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Product findOneWithDetails(Long id) {
		if (id == null || id < 1) {
			throw new SpecifiedEntityNotFoundException("product");
		}
		Product existedProduct = productRepository.findOne(id);
		if (null == existedProduct) {
			throw new SpecifiedEntityNotFoundException("product");
		}
		return existedProduct;
	}

	/*
	 * Private & Protected Methods
	 */
}

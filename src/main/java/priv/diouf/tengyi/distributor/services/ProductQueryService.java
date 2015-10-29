package priv.diouf.tengyi.distributor.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.product.Product;
import priv.diouf.tengyi.distributor.persistence.repositories.product.ProductRepository;
import priv.diouf.tengyi.distributor.services.criterias.product.ProductAdvancedSearchCriteria;
import priv.diouf.tengyi.distributor.services.criterias.product.ProductBasicSearchCriteria;
import priv.diouf.tengyi.distributor.services.exceptions.SpecifiedEntityNotFoundException;

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

	public Map<String, Long> generateStatistics() {
		return productRepository.generateStatistics();
	}

	public Product findOneWithAllDetails(long id) {
		if (id < 1) {
			throw new SpecifiedEntityNotFoundException("product");
		}
		Product existedProduct = productRepository.findOneWithAllDetails(id);
		if (existedProduct == null) {
			throw new SpecifiedEntityNotFoundException("product");
		}
		return existedProduct;
	}

	public Page<Product> findAll(ProductBasicSearchCriteria criteria, PageRequest pageRequest) {
		return productRepository.findAll(criteria, pageRequest);
	}

	public Page<Product> findAll(ProductAdvancedSearchCriteria criteria, PageRequest pageRequest) {
		return productRepository.findAll(criteria, pageRequest);
	}
	/*
	 * Private & Protected Methods
	 */
}

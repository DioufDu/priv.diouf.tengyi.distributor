package priv.diouf.tengyi.distributor.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.persistence.models.product.Product;
import priv.diouf.tengyi.distributor.services.ProductQueryService;
import priv.diouf.tengyi.distributor.web.models.requests.product.ProductAdvancedSearchRequest;
import priv.diouf.tengyi.distributor.web.models.requests.product.ProductBasicSearchRequest;
import priv.diouf.tengyi.distributor.web.models.responses.product.ProductDetail;
import priv.diouf.tengyi.distributor.web.models.responses.product.ProductInfo;
import priv.diouf.tengyi.distributor.web.models.responses.product.ProductPage;
import priv.diouf.tengyi.distributor.web.models.responses.product.ProductStatistics;

@RestController
public class ProductQueryController extends GeneralController {

	/*
	 * Fields
	 */

	@Autowired
	protected ProductQueryService productQueryService;

	@Autowired(required = false)
	@Qualifier("loginUser")
	protected Account loginUser;

	/*
	 * Retrieve Actions
	 */

	/*
	 * Retrieve Actions - Statistics
	 */

	@RequestMapping(value = "product", method = RequestMethod.GET)
	public ProductStatistics generateStatistics() {
		return new ProductStatistics(productQueryService.generateStatistics());
	}

	@RequestMapping(value = "product/login", method = RequestMethod.GET)
	public ProductStatistics testGetUser() {
		return new ProductStatistics(productQueryService.generateStatistics());
	}

	/*
	 * Retrieve Actions - Single
	 */

	@RequestMapping(value = "product/{productId}", method = RequestMethod.GET)
	public ProductDetail findOneWithAllDetails(@PathVariable("productId") long productId) {
		return new ProductDetail(productQueryService.findOneWithAllDetails(productId));
	}

	/*
	 * Retrieve Actions - Search & Page
	 */

	@RequestMapping(value = "product/search/basic", method = RequestMethod.POST)
	public ProductPage<ProductInfo> basicSearch(@RequestBody ProductBasicSearchRequest queryRequest) {
		return this.generateProductPage(productQueryService.findAll(queryRequest.getCriteria(), queryRequest.getPageRequest()));
	}

	@RequestMapping(value = "product/search/advanced", method = RequestMethod.POST)
	public ProductPage<ProductInfo> advancedSearch(@RequestBody ProductAdvancedSearchRequest queryRequest) {
		return this.generateProductPage(productQueryService.findAll(queryRequest.getCriteria(), queryRequest.getPageRequest()));
	}

	/*
	 * Others
	 */

	/*
	 * Protected & Private Methods
	 */

	private ProductPage<ProductInfo> generateProductPage(Page<Product> products) {
		return new ProductPage<ProductInfo>(products.getContent(), new ProductPage.Helper<ProductInfo>() {
			@Override
			protected ProductInfo createModel(Product product) {
				return new ProductInfo(product);
			}
		}, new PageRequest(products.getNumber(), products.getSize()), products.getTotalElements());
	}

}

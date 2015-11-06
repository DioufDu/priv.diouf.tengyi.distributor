package priv.diouf.tengyi.distributor.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.services.AccountQueryService;
import priv.diouf.tengyi.distributor.services.ProductMaintanceService;
import priv.diouf.tengyi.distributor.web.models.requests.product.ProductCreationRequest;
import priv.diouf.tengyi.distributor.web.models.requests.product.ProductUpdateRequest;
import priv.diouf.tengyi.distributor.web.models.responses.product.ProductDetail;

@RestController
public class ProductMaintanceController extends GeneralController {

	/*
	 * Fields
	 */

	@Autowired
	protected AccountQueryService accountQueryService;

	@Autowired
	protected ProductMaintanceService productMaintanceService;

	/*
	 * Create
	 */

	@RequestMapping(value = "product", method = RequestMethod.POST)
	@Autowired(required = false)
	public ProductDetail createProduct(@RequestBody(required = true) ProductCreationRequest request,
			@Qualifier("loginUser") Account loginUser) {
		return new ProductDetail(productMaintanceService.create(request, loginUser));
	}

	/*
	 * Update
	 */

	@RequestMapping(value = "product/{productId}", method = RequestMethod.PUT)
	@Autowired(required = false)
	public ProductDetail modifyProduct(@RequestBody(required = true) ProductUpdateRequest request,
			@PathVariable("productId") long productId, @Qualifier("loginUser") Account loginUser) {
		return new ProductDetail(productMaintanceService.update(productId, request, loginUser));
	}

	/*
	 * Delete
	 */

	@RequestMapping(value = "product/{productId}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable("productId") long productId) {
		productMaintanceService.delete(productId);
	}

	/*
	 * Others
	 */

	/*
	 * Protected & Private Methods
	 */
}

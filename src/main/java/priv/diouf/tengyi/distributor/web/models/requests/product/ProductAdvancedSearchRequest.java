package priv.diouf.tengyi.distributor.web.models.requests.product;

import priv.diouf.tengyi.distributor.services.criterias.product.ProductAdvancedSearchCriteria;
import priv.diouf.tengyi.distributor.web.models.requests.GeneralPageableRequest;

public class ProductAdvancedSearchRequest extends GeneralPageableRequest<ProductAdvancedSearchCriteria> {

	/*
	 * Fields
	 */

	/*
	 * Properties
	 */

	@Override
	public ProductAdvancedSearchCriteria getCriteria() {
		if (super.criteria == null) {
			super.criteria = new ProductAdvancedSearchCriteria();
		}
		return super.getCriteria();
	}
}

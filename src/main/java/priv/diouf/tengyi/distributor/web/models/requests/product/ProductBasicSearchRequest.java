package priv.diouf.tengyi.distributor.web.models.requests.product;

import priv.diouf.tengyi.distributor.services.criterias.product.ProductBasicSearchCriteria;
import priv.diouf.tengyi.distributor.web.models.requests.GeneralPageableRequest;

public class ProductBasicSearchRequest extends GeneralPageableRequest<ProductBasicSearchCriteria> {

	/*
	 * Fields
	 */

	/*
	 * Properties
	 */

	@Override
	public ProductBasicSearchCriteria getCriteria() {
		if (super.criteria == null) {
			super.criteria = new ProductBasicSearchCriteria();
		}
		return super.getCriteria();
	}

}

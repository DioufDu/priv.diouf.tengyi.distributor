package priv.diouf.tengyi.distributor.web.models.requests.account;

import priv.diouf.tengyi.distributor.services.models.AdvancedSearchCriteria;
import priv.diouf.tengyi.distributor.web.models.requests.GeneralPageableRequest;

public class AdvancedSearchRequest extends GeneralPageableRequest<AdvancedSearchCriteria> {

	/*
	 * Fields
	 */

	/*
	 * Properties
	 */

	@Override
	public AdvancedSearchCriteria getCriteria() {
		if (super.criteria == null) {
			super.criteria = new AdvancedSearchCriteria();
		}
		return super.getCriteria();
	}
}

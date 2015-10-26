package priv.diouf.tengyi.distributor.web.models.requests.account;

import priv.diouf.tengyi.distributor.services.models.BasicSearchCriteria;
import priv.diouf.tengyi.distributor.web.models.requests.GeneralPageableRequest;

public class BasicSearchRequest extends GeneralPageableRequest<BasicSearchCriteria> {

	/*
	 * Fields
	 */

	/*
	 * Properties
	 */

	@Override
	public BasicSearchCriteria getCriteria() {
		if (super.criteria == null) {
			super.criteria = new BasicSearchCriteria();
		}
		return super.getCriteria();
	}

}

package priv.diouf.tengyi.distributor.web.models.requests.account;

import priv.diouf.tengyi.distributor.services.criterias.account.AccountBasicSearchCriteria;
import priv.diouf.tengyi.distributor.web.models.requests.GeneralPageableRequest;

public class AccountBasicSearchRequest extends GeneralPageableRequest<AccountBasicSearchCriteria> {

	/*
	 * Fields
	 */

	/*
	 * Properties
	 */

	@Override
	public AccountBasicSearchCriteria getCriteria() {
		if (super.criteria == null) {
			super.criteria = new AccountBasicSearchCriteria();
		}
		return super.getCriteria();
	}

}

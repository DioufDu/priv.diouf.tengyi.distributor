package priv.diouf.tengyi.distributor.web.models.requests.account;

import priv.diouf.tengyi.distributor.services.criterias.account.AccountAdvancedSearchCriteria;
import priv.diouf.tengyi.distributor.web.models.requests.GeneralPageableRequest;

public class AccountAdvancedSearchRequest extends GeneralPageableRequest<AccountAdvancedSearchCriteria> {

	/*
	 * Fields
	 */

	/*
	 * Properties
	 */

	@Override
	public AccountAdvancedSearchCriteria getCriteria() {
		if (super.criteria == null) {
			super.criteria = new AccountAdvancedSearchCriteria();
		}
		return super.getCriteria();
	}
}

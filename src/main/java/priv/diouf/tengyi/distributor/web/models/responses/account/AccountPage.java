package priv.diouf.tengyi.distributor.web.models.responses.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import priv.diouf.tengyi.distributor.persistence.models.account.Account;

public class AccountPage<TAccountModel> extends PageImpl<TAccountModel> implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Constructors
	 */

	public AccountPage(Collection<Account> content, Helper<TAccountModel> helper, Pageable pageable, long total) {
		super(createModels(content, helper), pageable, total);
	}

	/*
	 * Actions
	 */

	public static <TAccountModel> ArrayList<TAccountModel> createModels(Collection<Account> accounts, Helper<TAccountModel> helper) {
		ArrayList<TAccountModel> items = null;
		if (CollectionUtils.isEmpty(accounts)) {
			items = new ArrayList<TAccountModel>(1);
		} else {
			items = new ArrayList<TAccountModel>(accounts.size());
			for (Account account : accounts) {
				items.add(helper.createModel(account));
			}
		}
		return items;
	}

	public static abstract class Helper<TAccountModel> {
		protected abstract TAccountModel createModel(Account account);
	}

}

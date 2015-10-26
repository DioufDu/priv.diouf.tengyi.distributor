package priv.diouf.tengyi.distributor.web.models.responses.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import priv.diouf.tengyi.distributor.persistence.models.account.Account;

public class AccountPage extends PageImpl<AccountDetail> implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -9142298842946763771L;

	/*
	 * Constructors
	 */

	public AccountPage(List<AccountDetail> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

	/*
	 * Actions
	 */

	public static ArrayList<AccountDetail> createDetails(Collection<Account> accounts) {
		ArrayList<AccountDetail> items = null;
		if (CollectionUtils.isEmpty(accounts)) {
			items = new ArrayList<AccountDetail>(1);
		} else {
			items = new ArrayList<AccountDetail>(accounts.size());
			for (Account account : accounts) {
				items.add(new AccountDetail(account));
			}
		}
		return items;
	}
}

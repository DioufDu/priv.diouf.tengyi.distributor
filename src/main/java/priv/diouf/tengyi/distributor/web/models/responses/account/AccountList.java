package priv.diouf.tengyi.distributor.web.models.responses.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.util.CollectionUtils;

import priv.diouf.tengyi.distributor.persistence.models.account.Account;

public class AccountList extends ArrayList<AccountDetail> implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -9142298842946763771L;

	/*
	 * Constructors
	 */

	public AccountList(Collection<Account> accounts) {
		super();
		if (!CollectionUtils.isEmpty(accounts)) {
			for (Account account : accounts) {
				this.add(new AccountDetail(account));
			}
		}
	}

}

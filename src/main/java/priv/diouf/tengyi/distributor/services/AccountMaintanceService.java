package priv.diouf.tengyi.distributor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.persistence.repositories.account.AccountRepository;
import priv.diouf.tengyi.distributor.web.models.requests.account.CreationRequest;
import priv.diouf.tengyi.distributor.web.models.requests.account.UpdateRequest;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AccountMaintanceService {

	/*
	 * Fields
	 */

	@Autowired
	protected AccountRepository accountRepository;

	/*
	 * CUD Actions
	 */

	@Transactional
	public Account create(CreationRequest request) {
		// TODO
		return null;
	}

	@Transactional
	public Account update(long accountId, UpdateRequest request) {
		// TODO
		return null;
	}

	@Transactional
	public void delete(long accountId) {
		// TODO
	}

	/*
	 * Private & Protected Methods
	 */

	protected Account migrateScalarProperties(CreationRequest request, Account account) {
		// TODO
		return null;
	}
}

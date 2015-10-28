package priv.diouf.tengyi.distributor.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.common.models.enums.AccountType;
import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.persistence.repositories.account.AccountRepository;
import priv.diouf.tengyi.distributor.services.criterias.account.AccountAdvancedSearchCriteria;
import priv.diouf.tengyi.distributor.services.criterias.account.AccountBasicSearchCriteria;
import priv.diouf.tengyi.distributor.services.exceptions.SpecifiedEntityNotFoundException;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AccountQueryService {

	/*
	 * Fields
	 */

	protected static final String DEFAULT_SORT_PROPERTIES = "id";

	@Autowired
	protected AccountRepository accountRepository;

	/*
	 * Queries
	 */

	public Map<AccountType, Long> generateStatistics() {
		return accountRepository.generateStatistics();
	}

	public Account findOneWithAllDetails(long id) {
		if (id < 1) {
			throw new SpecifiedEntityNotFoundException("account");
		}
		Account existedAccount = accountRepository.findOneWithAllDetails(id);
		if (existedAccount == null) {
			throw new SpecifiedEntityNotFoundException("account");
		}
		return existedAccount;
	}

	public Page<Account> findAll(AccountBasicSearchCriteria criteria, PageRequest pageRequest) {
		return accountRepository.findAll(criteria, pageRequest);
	}

	public Page<Account> findAll(AccountAdvancedSearchCriteria criteria, PageRequest pageRequest) {
		return accountRepository.findAll(criteria, pageRequest);
	}

	/*
	 * Private & Protected Methods
	 */
}

package priv.diouf.tengyi.distributor.services;

import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.persistence.repositories.account.AccountRepository;
import priv.diouf.tengyi.distributor.services.exceptions.SpecifiedEntityNotFoundException;
import priv.diouf.tengyi.distributor.services.models.AdvancedSearchCriteria;
import priv.diouf.tengyi.distributor.services.models.BasicSearchCriteria;

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

	public Map<Calendar, long[]> generateStatistics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public Page<Account> findAll(AdvancedSearchCriteria criteria, PageRequest pageRequest) {
		// TODO
		return null;// accountRepository.query(criteria, pageRequest);
	}

	@Transactional(readOnly = true)
	public Page<Account> findAll(BasicSearchCriteria criteria, PageRequest pageRequest) {
		// TODO
		return null;// accountRepository.query(criteria, pageRequest);
	}

	@Transactional(readOnly = true)
	public Page<Account> findAll(Pageable pageable) {
		return accountRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Account findOneWithDetails(long id) {
		if (id < 1) {
			throw new SpecifiedEntityNotFoundException("account");
		}
		Account existedAccount = accountRepository.findOne(id);
		if (null == existedAccount) {
			throw new SpecifiedEntityNotFoundException("account");
		}
		return existedAccount;
	}

	/*
	 * Private & Protected Methods
	 */
}

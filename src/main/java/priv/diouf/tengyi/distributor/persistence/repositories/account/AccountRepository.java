package priv.diouf.tengyi.distributor.persistence.repositories.account;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.persistence.models.account.Account_;
import priv.diouf.tengyi.distributor.persistence.repositories.GeneralJpaRepository;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Transactional
public class AccountRepository extends GeneralJpaRepository<Account, Account_, Long> {

	/*
	 * Constructors
	 */

	@Autowired
	public AccountRepository(EntityManager entityManager) {
		super(Account.class, entityManager);
	}

	/*
	 * Actions
	 */

}

package priv.diouf.tengyi.distributor.persistence.initializer.factories.account;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.initializer.factories.EntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.GeneralEntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.TransactionalDataEntityFactory;
import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.persistence.repositories.account.AccountRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.photo.PhotoRepository;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountEntityFactory extends GeneralEntityFactory<Account> implements TransactionalDataEntityFactory<Account> {

	/*
	 * Fields
	 */

	@Autowired
	protected AccountRepository dishRepository;

	@Autowired
	protected PhotoRepository photoRepository;

	/*
	 * Creations
	 */

	@Override
	@Transactional
	protected void fulfill(List<Account> allCreatedEntities) {
		for (int dayOff = -RandomUtils.nextInt(10, 15); dayOff < RandomUtils.nextInt(10, 15); dayOff++) {
			Calendar offerDate = Calendar.getInstance();
			offerDate.add(Calendar.DATE, dayOff);
			for (int idx = 0; idx < RandomUtils.nextInt(10, 50); idx++) {
				Account account = new Account();
				// TODO: Scalar Properties
				// TODO: Navigations
				// TODO: Modification
				Calendar createOn = Calendar.getInstance();
				int pastCreateOnDayCount = RandomUtils.nextInt(0, 10);
				createOn.add(Calendar.DATE, dayOff - pastCreateOnDayCount);
				account.getModification().setCreateOn(createOn);
				Calendar updateOn = Calendar.getInstance();
				if (pastCreateOnDayCount > 1) {
					updateOn.add(Calendar.DATE, dayOff - RandomUtils.nextInt(0, pastCreateOnDayCount));
				}
				account.getModification().setUpdateOn(updateOn);
				// Save
				allCreatedEntities.add(account);
			}
		}
	}

	/*
	 * Metadata Information
	 */

	@Override
	protected EntityFactory<?>[] getDependEntityFactories() {
		return new EntityFactory<?>[0];
	}

	@Override
	public Class<Account> getEntityType() {
		return Account.class;
	}

	@Override
	protected JpaRepository<Account, Long> getGeneralJpaRepository() {
		return dishRepository;
	}

	/*
	 * Private & Protected Methods
	 */
}

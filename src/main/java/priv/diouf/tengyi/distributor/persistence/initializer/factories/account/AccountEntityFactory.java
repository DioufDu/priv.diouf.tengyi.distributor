package priv.diouf.tengyi.distributor.persistence.initializer.factories.account;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.common.models.enums.AccountStatus;
import priv.diouf.tengyi.distributor.common.models.enums.AccountType;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.EntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.GeneralEntityFactory;
import priv.diouf.tengyi.distributor.persistence.initializer.factories.TransactionalDataEntityFactory;
import priv.diouf.tengyi.distributor.persistence.models.Modification;
import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.persistence.models.account.Address;
import priv.diouf.tengyi.distributor.persistence.models.account.Administrator;
import priv.diouf.tengyi.distributor.persistence.models.account.AuthReseller;
import priv.diouf.tengyi.distributor.persistence.models.account.Contact;
import priv.diouf.tengyi.distributor.persistence.models.account.TrialReseller;
import priv.diouf.tengyi.distributor.persistence.repositories.account.AccountRepository;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountEntityFactory extends GeneralEntityFactory<Account> implements TransactionalDataEntityFactory<Account> {

	/*
	 * Fields
	 */

	private static final String[] TITLES = new String[] { "Mr", "Mrs", "Ms", "Miss" };

	@Autowired
	protected AccountRepository accountRepository;

	/*
	 * Creations
	 */

	@Override
	@Transactional
	protected void fulfill(List<Account> allCreatedEntities) {
		// Super Admin
		Administrator admin = this.createAdmin();
		allCreatedEntities.add(admin);
		// Other Accounts
		for (int idx = 24; idx > 0; idx--) {
			Account account = null;
			AccountType accountType = AccountType.values()[RandomUtils.nextInt(0, AccountType.values().length)];
			String accountNo = String.valueOf(RandomUtils.nextLong(10000, 99999));
			switch (accountType) {
			case ADMIN: {
				account = new Administrator();
				account.setLoginId("admin" + accountNo);
				account.setName(account.getLoginId().replace("admin", "Administrator"));
			}
				break;
			case AUTH_RESELLER: {
				account = new AuthReseller();
				account.setLoginId("areseller" + accountNo);
				account.setName(account.getLoginId().replace("areseller", "AuthenticatedReseller"));
			}
				break;
			case TRIAL_RESELLER: {
				account = new TrialReseller();
				account.setLoginId("treseller" + accountNo);
				account.setName(account.getLoginId().replace("treseller", "TrialReseller"));
			}
				break;
			}

			// Scalar Properties
			account.setTitle(TITLES[RandomUtils.nextInt(0, TITLES.length)]);
			account.setClearPassword("123456");

			if (RandomUtils.nextInt(0, 20) < 19) {
				account.setStatus(AccountStatus.ACTIVE);
			} else {
				account.setStatus(AccountStatus.LOCKED);
			}
			// Navigations - Contact
			account.setContact(new Contact());
			account.getContact().setAccount(account);
			account.getContact().setEmail(String.format("%s@163.com", RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(5, 10))));
			account.getContact().setFax(String.valueOf(RandomUtils.nextLong(10000000, 90000000)));
			account.getContact().setTelephone(String.valueOf(RandomUtils.nextLong(10000000, 90000000)));
			account.getContact().setCellphone(String.valueOf(RandomUtils.nextLong(13000000000L, 19000000000L)));
			if (RandomUtils.nextInt(1, 3) == 2) {
				account.getContact().setAlternativePhone(String.valueOf(RandomUtils.nextLong(13000000000L, 19000000000L)));
			}
			// Navigations - Address
			account.setAddress(new Address());
			account.getAddress().setAccount(account);
			account.getAddress().setCountry("China");
			account.getAddress().setProvince("Sichuan");
			account.getAddress().setCity("Chengdu");
			account.getAddress().setZone("High-Tech Zone");
			account.getAddress().setZip("610041");
			account.getAddress().setOverall("1/F, No.682 Xingwen Street, Suining, Sichuan, China");
			// Navigations - Modification
			account.setModification(new Modification(admin));
			Calendar createOn = Calendar.getInstance();
			createOn.add(Calendar.DATE, -RandomUtils.nextInt(30, 60));
			account.getModification().setCreateOn(createOn);
			Calendar updateOn = Calendar.getInstance();
			updateOn.add(Calendar.DATE, -RandomUtils.nextInt(3, 30));
			account.getModification().setUpdateOn(updateOn);
			// Save
			allCreatedEntities.add(account);
		}
	}

	private Administrator createAdmin() {
		Administrator admin = new Administrator();
		// Scalar Properties
		admin.setTitle(TITLES[RandomUtils.nextInt(0, TITLES.length)]);
		admin.setClearPassword("123456");
		admin.setType(AccountType.values()[RandomUtils.nextInt(0, AccountType.values().length)]);
		admin.setLoginId("superadmin");
		admin.setName("Super Administrator");
		admin.setStatus(AccountStatus.ACTIVE);
		// Navigations - Contact
		admin.setContact(new Contact());
		admin.getContact().setAccount(admin);
		admin.getContact().setEmail(String.format("%s@163.com", RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(5, 10))));
		admin.getContact().setFax(String.valueOf(RandomUtils.nextLong(10000000, 90000000)));
		admin.getContact().setTelephone(String.valueOf(RandomUtils.nextLong(10000000, 90000000)));
		admin.getContact().setCellphone(String.valueOf(RandomUtils.nextLong(13000000000L, 19000000000L)));
		if (RandomUtils.nextInt(1, 3) == 2) {
			admin.getContact().setAlternativePhone(String.valueOf(RandomUtils.nextLong(13000000000L, 19000000000L)));
		}
		// Navigations - Address
		admin.setAddress(new Address());
		admin.getAddress().setAccount(admin);
		admin.getAddress().setCountry("China");
		admin.getAddress().setProvince("Sichuan");
		admin.getAddress().setCity("Chengdu");
		admin.getAddress().setZone("High-Tech Zone");
		admin.getAddress().setZip("610041");
		admin.getAddress().setOverall("1/F, No.682 Xingwen Street, Suining, Sichuan, China");
		// Navigations - Modification
		admin.setModification(new Modification(admin));
		Calendar createOn = Calendar.getInstance();
		createOn.add(Calendar.DATE, -RandomUtils.nextInt(30, 60));
		admin.getModification().setCreateOn(createOn);
		Calendar updateOn = Calendar.getInstance();
		updateOn.add(Calendar.DATE, -RandomUtils.nextInt(3, 30));
		admin.getModification().setUpdateOn(updateOn);
		// Return
		return admin;
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
		return accountRepository;
	}

	/*
	 * Private & Protected Methods
	 */
}

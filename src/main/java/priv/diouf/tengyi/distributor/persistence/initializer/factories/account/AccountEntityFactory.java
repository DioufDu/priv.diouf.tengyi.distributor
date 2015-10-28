package priv.diouf.tengyi.distributor.persistence.initializer.factories.account;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.common.models.enums.AccountStatus;
import priv.diouf.tengyi.distributor.common.models.enums.AccountType;
import priv.diouf.tengyi.distributor.persistence.exceptions.CommonPersistenceException;
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
import priv.diouf.tengyi.distributor.persistence.models.photo.AvatarPhotoGroup;
import priv.diouf.tengyi.distributor.persistence.repositories.account.AccountRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.account.AvatarPhotoGroupRepository;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AccountEntityFactory extends GeneralEntityFactory<Account> implements TransactionalDataEntityFactory<Account> {

	/*
	 * Fields
	 */

	private static final String[] TITLES = new String[] { "Mr", "Mrs", "Ms", "Miss" };

	@Autowired
	protected AccountRepository accountRepository;

	@Autowired
	protected AvatarPhotoGroupRepository avatarPhotoGroupRepository;

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
			// Type Definition
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
			// Navigations
			account.setContact(this.generateContact(account));
			account.setAddress(this.generateAddress(account));
			account.setModification(this.generateModification(admin));
			account.setAvatarPhotoGroup(this.generateAvatar(admin));
			// Save
			allCreatedEntities.add(account);
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
		return accountRepository;
	}

	/*
	 * Private & Protected Methods
	 */

	private Contact generateContact(Account account) {
		Contact contact = new Contact(account);
		// Navigations - Contact
		contact.setEmail(String.format("%s@163.com", RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(5, 10))));
		contact.setFax(String.valueOf(RandomUtils.nextLong(10000000, 90000000)));
		contact.setTelephone(String.valueOf(RandomUtils.nextLong(10000000, 90000000)));
		contact.setCellphone(String.valueOf(RandomUtils.nextLong(13000000000L, 19000000000L)));
		if (RandomUtils.nextInt(1, 3) == 2) {
			contact.setAlternativePhone(String.valueOf(RandomUtils.nextLong(13000000000L, 19000000000L)));
		}
		// Return
		return contact;
	}

	private Address generateAddress(Account account) {
		Address address = new Address(account);
		// Navigations - Address
		address.setCountry("China");
		address.setProvince("Sichuan");
		address.setCity("Chengdu");
		address.setZone("High-Tech Zone");
		address.setZip("610041");
		address.setOverall("1/F, No.682 Xingwen Street, Suining, Sichuan, China");
		// Return
		return address;
	}

	private Modification generateModification(Account account) {
		Modification modification = new Modification(account);
		// Navigations - Modification
		Calendar createOn = Calendar.getInstance();
		createOn.add(Calendar.DATE, -RandomUtils.nextInt(30, 60));
		modification.setCreateOn(createOn);
		Calendar updateOn = Calendar.getInstance();
		updateOn.add(Calendar.DATE, -RandomUtils.nextInt(3, 30));
		modification.setUpdateOn(updateOn);
		// Return
		return modification;
	}

	private AvatarPhotoGroup generateAvatar(Account account) {
		// Navigations - Avatar
		AvatarPhotoGroup avatarPhotoGroup = new AvatarPhotoGroup(account);
		// Navigations - Modification
		avatarPhotoGroup.setModification(new Modification(account));
		Calendar createOn = Calendar.getInstance();
		createOn.add(Calendar.DATE, -RandomUtils.nextInt(30, 60));
		avatarPhotoGroup.getModification().setCreateOn(createOn);
		Calendar updateOn = Calendar.getInstance();
		updateOn.add(Calendar.DATE, -RandomUtils.nextInt(3, 30));
		avatarPhotoGroup.getModification().setUpdateOn(updateOn);
		String photoName = "avatars/" + RandomUtils.nextInt(1, 27) + ".jpg";
		try (InputStream avatarStream = this.getClass().getResourceAsStream(photoName)) {
			if (avatarStream == null || avatarStream.available() < 1) {
				return avatarPhotoGroup;
			}
			return avatarPhotoGroupRepository.generatePhotoGroup(avatarPhotoGroup, IOUtils.toByteArray(avatarStream));
		} catch (IOException ex) {
			throw new CommonPersistenceException("Can't store the avatar into account.", ex);
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
		admin.setContact(this.generateContact(admin));
		// Navigations - Address
		admin.setAddress(this.generateAddress(admin));
		// Navigations - Modification
		admin.setModification(this.generateModification(admin));
		// Navigations - Avatar
		admin.setAvatarPhotoGroup(this.generateAvatar(admin));
		// Return
		return admin;
	}

}

package priv.diouf.tengyi.distributor.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.Modification;
import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.persistence.models.account.Address;
import priv.diouf.tengyi.distributor.persistence.models.account.Contact;
import priv.diouf.tengyi.distributor.persistence.models.photo.AvatarPhotoGroup;
import priv.diouf.tengyi.distributor.persistence.repositories.account.AccountRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.account.AvatarPhotoGroupRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.photo.FullScreenPhotoRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.photo.OriginalPhotoRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.photo.StandardPhotoRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.photo.ThumbnailPhotoRepository;
import priv.diouf.tengyi.distributor.services.exceptions.SpecifiedEntityNotFoundException;
import priv.diouf.tengyi.distributor.web.models.requests.account.AccountCreationRequest;
import priv.diouf.tengyi.distributor.web.models.requests.account.AccountUpdateRequest;
import priv.diouf.tengyi.distributor.web.models.requests.account.AddressMergeRequest;
import priv.diouf.tengyi.distributor.web.models.requests.account.ContactMergeRequest;
import priv.diouf.tengyi.distributor.web.models.requests.photo.PhotoGroupMergeRequest;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AccountMaintanceService {

	/*
	 * Fields
	 */

	@Autowired
	protected AccountRepository accountRepository;

	@Autowired
	protected AvatarPhotoGroupRepository avatarPhotoGroupRepository;

	@Autowired
	protected ThumbnailPhotoRepository thumbnailPhotoRepository;

	@Autowired
	protected StandardPhotoRepository standardPhotoRepository;

	@Autowired
	protected FullScreenPhotoRepository fullScreenPhotoRepository;

	@Autowired
	protected OriginalPhotoRepository originalPhotoRepository;

	/*
	 * CUD Actions
	 */

	public Account create(AccountCreationRequest request) {
		Account account = new Account();
		// Scalar Properties
		this.migrateScalarProperties(request, account);
		// Navigations - Contact
		account.setContact(this.migrateContact(request.getContact(), account.getContact()));
		// Navigations - Address
		account.setAddress(this.migrateAddress(request.getAddress(), account.getAddress()));
		// Navigations - Modification
		account.setModification(this.migrateModification(new Modification(account), account));
		// Navigations - Avatar
		account.setAvatarPhotoGroup(this.migrateAvatar(request.getAvatar(), new AvatarPhotoGroup(account), account));
		// Return
		return account;
	}

	public Account update(long accountId, AccountUpdateRequest request) {
		// Validation
		if (accountId < 1) {
			throw new SpecifiedEntityNotFoundException("account");
		}
		Account account = accountRepository.findOneWithAllDetails(accountId);
		if (account == null) {
			throw new SpecifiedEntityNotFoundException("account");
		}
		// Scalar Properties
		this.migrateScalarProperties(request, account);
		// Navigations - Contact
		account.setContact(this.migrateContact(request.getContact(), account.getContact()));
		// Navigations - Address
		account.setAddress(this.migrateAddress(request.getAddress(), account.getAddress()));
		// Navigations - Modification
		account.setModification(this.migrateModification(new Modification(account), account));
		// Navigations - Avatar
		account.setAvatarPhotoGroup(this.migrateAvatar(request.getAvatar(), new AvatarPhotoGroup(account), account));
		// Return
		return account;
	}

	public void delete(long accountId) {
		// Validation
		if (accountId < 1 || !accountRepository.exists(accountId)) {
			throw new SpecifiedEntityNotFoundException("account");
		}
		// Delete
		accountRepository.delete(accountId);
	}

	/*
	 * Private & Protected Methods
	 */

	protected Account migrateScalarProperties(AccountCreationRequest request, Account account) {
		// Scalar Fields
		account.setLoginId(request.getLoginId());
		account.setName(request.getName());
		account.setTitle(request.getTitle());
		account.setType(request.getType());
		account.setStatus(request.getStatus());
		// Return
		return account;
	}

	/*
	 * Private & Protected Methods
	 */

	private Contact migrateContact(ContactMergeRequest request, Contact contact) {
		if (request == null) {
			return contact;
		}
		if (contact == null) {
			contact = new Contact();
		}
		// Navigations - Contact
		contact.setEmail(request.getEmail());
		contact.setFax(request.getFax());
		contact.setTelephone(request.getTelephone());
		contact.setMobile(request.getCellphone());
		contact.setAlternativePhone(request.getAlternativePhone());
		// Return
		return contact;
	}

	private Address migrateAddress(AddressMergeRequest request, Address address) {
		if (request == null) {
			return address;
		}
		if (address == null) {
			address = new Address();
		}
		// Navigations - Address
		address.setCountry(request.getCountry());
		address.setProvince(request.getProvince());
		address.setCity(request.getCity());
		address.setZone(request.getZone());
		address.setZip(request.getZip());
		address.setOverall(request.getOverall());
		// Return
		return address;
	}

	private Modification migrateModification(Modification modification, Account account) {
		if (modification == null) {
			return new Modification(account);
		}
		// Navigations - Modification
		if (modification.getCreateBy() == null && account != null) {
			modification.setCreateBy(account);
		}
		if (modification.getCreateOn() == null) {
			modification.setCreateOn(Calendar.getInstance());
		}
		if (account != null) {
			modification.setUpdateBy(account);
		}
		modification.setUpdateOn(Calendar.getInstance());
		// Return
		return modification;
	}

	private AvatarPhotoGroup migrateAvatar(PhotoGroupMergeRequest request, AvatarPhotoGroup avatarPhotoGroup, Account account) {
		if (request == null) {
			return avatarPhotoGroup;
		}
		if (avatarPhotoGroup == null) {
			avatarPhotoGroup = new AvatarPhotoGroup();
		}
		// Navigations - Avatar
		if (request.getThumbnailPhotoId() != null && !request.getThumbnailPhotoId().equals(avatarPhotoGroup.getThumbnailPhoto().getId())) {
			avatarPhotoGroup.setThumbnailPhoto(thumbnailPhotoRepository.findOne(request.getThumbnailPhotoId()));
		}
		if (request.getStandardPhotoId() != null && !request.getStandardPhotoId().equals(avatarPhotoGroup.getStandardPhoto().getId())) {
			avatarPhotoGroup.setStandardPhoto(standardPhotoRepository.findOne(request.getStandardPhotoId()));
		}
		if (request.getFullScreenPhotoId() != null && !request.getFullScreenPhotoId().equals(avatarPhotoGroup.getFullScreenPhoto()
				.getId())) {
			avatarPhotoGroup.setFullScreenPhoto(fullScreenPhotoRepository.findOne(request.getFullScreenPhotoId()));
		}
		if (request.getOriginalPhotoId() != null && !request.getOriginalPhotoId().equals(avatarPhotoGroup.getOriginalPhoto().getId())) {
			avatarPhotoGroup.setOriginalPhoto(originalPhotoRepository.findOne(request.getOriginalPhotoId()));
		}
		// Navigations - Modification
		if (avatarPhotoGroup.getModification() == null) {
			avatarPhotoGroup.setModification(new Modification(account));
		}
		this.migrateModification(avatarPhotoGroup.getModification(), account);
		// Return
		return avatarPhotoGroup;
	}
}

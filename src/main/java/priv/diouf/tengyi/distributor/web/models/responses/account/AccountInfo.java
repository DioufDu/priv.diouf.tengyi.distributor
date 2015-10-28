package priv.diouf.tengyi.distributor.web.models.responses.account;

import java.io.Serializable;

import priv.diouf.tengyi.distributor.common.models.enums.AccountStatus;
import priv.diouf.tengyi.distributor.common.models.enums.AccountType;
import priv.diouf.tengyi.distributor.persistence.models.account.Account;

public class AccountInfo implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Fields
	 */

	protected long id;

	protected String loginId;

	protected String name;

	protected String title;

	protected AccountType type;

	protected AccountStatus status;

	/*
	 * Navigations
	 */

	protected ContactInfo contact;

	protected AddressInfo address;

	/*
	 * Constructors
	 */

	public AccountInfo(Account account) {
		if (account == null) {
			return;
		}
		// Scalar Fields
		this.setId(account.getId());
		this.setLoginId(account.getLoginId());
		this.setName(account.getName());
		this.setTitle(account.getTitle());
		this.setType(account.getType());
		this.setStatus(account.getStatus());
		// Navigations
		this.setContact(new ContactInfo(account.getContact()));
		this.setAddress(new AddressInfo(account.getAddress()));
	}

	/*
	 * Properties
	 */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public ContactInfo getContact() {
		return contact;
	}

	public void setContact(ContactInfo contact) {
		this.contact = contact;
	}

	public AddressInfo getAddress() {
		return address;
	}

	public void setAddress(AddressInfo address) {
		this.address = address;
	}

}

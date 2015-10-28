package priv.diouf.tengyi.distributor.services.criterias.account;

import java.io.Serializable;

import priv.diouf.tengyi.distributor.common.models.enums.AccountStatus;
import priv.diouf.tengyi.distributor.common.models.enums.AccountType;

public class AccountAdvancedSearchCriteria implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Fields
	 */
	protected String loginId;

	protected String name;

	protected String title;

	protected AccountType type;

	protected AccountStatus status;

	protected String addressOverall;

	protected String contactPhone;

	protected String contactEmail;

	/*
	 * Properties
	 */

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

	public String getAddressOverall() {
		return addressOverall;
	}

	public void setAddressOverall(String addressOverall) {
		this.addressOverall = addressOverall;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

}

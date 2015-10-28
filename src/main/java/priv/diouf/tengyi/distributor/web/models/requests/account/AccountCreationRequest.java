package priv.diouf.tengyi.distributor.web.models.requests.account;

import java.io.Serializable;

import priv.diouf.tengyi.distributor.common.models.enums.AccountStatus;
import priv.diouf.tengyi.distributor.common.models.enums.AccountType;
import priv.diouf.tengyi.distributor.web.models.requests.photo.PhotoGroupMergeRequest;

public class AccountCreationRequest implements Serializable {

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

	protected AddressMergeRequest address;

	protected ContactMergeRequest contact;

	protected PhotoGroupMergeRequest avatar;

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

	public AddressMergeRequest getAddress() {
		return address;
	}

	public void setAddress(AddressMergeRequest address) {
		this.address = address;
	}

	public ContactMergeRequest getContact() {
		return contact;
	}

	public void setContact(ContactMergeRequest contact) {
		this.contact = contact;
	}

	public PhotoGroupMergeRequest getAvatar() {
		return avatar;
	}

	public void setAvatar(PhotoGroupMergeRequest photo) {
		this.avatar = photo;
	}

}

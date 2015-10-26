package priv.diouf.tengyi.distributor.persistence.models.account;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import priv.diouf.tengyi.distributor.common.models.enums.AccountStatus;
import priv.diouf.tengyi.distributor.common.models.enums.AccountType;
import priv.diouf.tengyi.distributor.persistence.PersistenceConfig;
import priv.diouf.tengyi.distributor.persistence.models.Modification;
import priv.diouf.tengyi.distributor.persistence.models.photo.AvatarPhotoGroup;

@Entity
@Table(name = "T_ACCOUNT", schema = PersistenceConfig.PERSISTENCE_SCHEMA_NAME)
public class Account implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */

	@Id
	@GeneratedValue
	@Column(name = "ID")
	protected long id;

	/*
	 * Fields
	 */

	@Column(name = "LOGIN_ID")
	protected String loginId;

	@Column(name = "NAME")
	protected String name;

	@Column(name = "TITLE")
	protected String title;

	@Column(name = "CLEAR_PASSWORD")
	protected String clearPassword;

	@Column(name = "TYPE")
	@Enumerated(EnumType.STRING)
	protected AccountType type;

	@Column(name = "Status")
	@Enumerated(EnumType.STRING)
	protected AccountStatus status;

	/*
	 * Embed
	 */

	/*
	 * Navigations
	 */

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
	@JoinColumn(name = "AVATAR_PHOTO_GROUP_ID")
	protected AvatarPhotoGroup avatarPhotoGroup;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
	@JoinColumn(name = "CONTACT_ID")
	protected Contact contact;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
	@JoinColumn(name = "ADDRESS_ID")
	protected Address address;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
	@JoinColumn(name = "MODIFICATION_ID")
	protected Modification modification;

	/*
	 * Constructors
	 */

	public Account() {
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

	public String getClearPassword() {
		return clearPassword;
	}

	public void setClearPassword(String clearPassword) {
		this.clearPassword = clearPassword;
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

	public AvatarPhotoGroup getAvatarPhotoGroup() {
		return avatarPhotoGroup;
	}

	public void setAvatarPhotoGroup(AvatarPhotoGroup avatarPhotoGroup) {
		this.avatarPhotoGroup = avatarPhotoGroup;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Modification getModification() {
		return modification;
	}

	public void setModification(Modification modification) {
		this.modification = modification;
	}

}

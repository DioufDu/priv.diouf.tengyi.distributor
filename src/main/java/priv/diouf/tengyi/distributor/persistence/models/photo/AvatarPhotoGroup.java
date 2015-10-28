package priv.diouf.tengyi.distributor.persistence.models.photo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import priv.diouf.tengyi.distributor.persistence.models.account.Account;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("AVATAR")
public class AvatarPhotoGroup extends PhotoGroup implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Navigations
	 */

	@OneToOne(mappedBy = "avatarPhotoGroup", cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = false)
	// @JoinColumn(name = "ACCOUNT_ID")
	protected Account account;

	/*
	 * Constructors
	 */

	public AvatarPhotoGroup() {

	}

	public AvatarPhotoGroup(Account account) {
		this.setAccount(account);
	}

	/*
	 * Properties
	 */

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}

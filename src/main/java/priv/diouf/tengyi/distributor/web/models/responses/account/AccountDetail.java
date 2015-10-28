package priv.diouf.tengyi.distributor.web.models.responses.account;

import java.io.Serializable;

import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.web.models.responses.ModificationDetail;
import priv.diouf.tengyi.distributor.web.models.responses.photo.PhotoGroupInfo;

public class AccountDetail extends AccountInfo implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Fields
	 */

	/*
	 * Navigations
	 */

	protected ModificationDetail modification;

	protected PhotoGroupInfo avatar;

	/*
	 * Constructors
	 */

	public AccountDetail(Account account) {
		super(account);
		if (account == null) {
			return;
		}
		// Scalar Fields
		// Navigations
		this.setModification(new ModificationDetail(account.getModification()));
		this.setAvatar(new PhotoGroupInfo(account.getAvatarPhotoGroup()));
	}

	/*
	 * Properties
	 */

	public ModificationDetail getModification() {
		return modification;
	}

	public void setModification(ModificationDetail modification) {
		this.modification = modification;
	}

	public PhotoGroupInfo getAvatar() {
		return avatar;
	}

	public void setAvatar(PhotoGroupInfo avatar) {
		this.avatar = avatar;
	}

}

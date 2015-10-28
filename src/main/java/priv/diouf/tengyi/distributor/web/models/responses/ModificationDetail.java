package priv.diouf.tengyi.distributor.web.models.responses;

import java.io.Serializable;

import priv.diouf.tengyi.distributor.persistence.models.Modification;
import priv.diouf.tengyi.distributor.web.models.responses.account.AccountInfo;

public class ModificationDetail extends ModificationInfo implements Serializable {

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

	protected AccountInfo createBy;

	protected AccountInfo updateBy;

	/*
	 * Constructors
	 */

	public ModificationDetail(Modification modification) {
		super(modification);
		if (modification == null) {
			return;
		}
		// Scalar Fields
		// Navigations
		this.setCreateBy(new AccountInfo(modification.getCreateBy()));
		this.setUpdateBy(new AccountInfo(modification.getUpdateBy()));
	}

	/*
	 * Properties
	 */

	public AccountInfo getCreateBy() {
		return createBy;
	}

	public void setCreateBy(AccountInfo createBy) {
		this.createBy = createBy;
	}

	public AccountInfo getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(AccountInfo updateBy) {
		this.updateBy = updateBy;
	}

}

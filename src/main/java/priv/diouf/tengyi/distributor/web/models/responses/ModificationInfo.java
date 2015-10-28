package priv.diouf.tengyi.distributor.web.models.responses;

import java.io.Serializable;
import java.util.Calendar;

import priv.diouf.tengyi.distributor.persistence.models.Modification;

public class ModificationInfo implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Fields
	 */

	protected long id;

	protected Calendar createOn;

	protected Calendar updateOn;

	/*
	 * Navigations
	 */

	protected long createById;

	protected long updateById;

	/*
	 * Constructors
	 */

	public ModificationInfo(Modification modification) {
		if (modification == null) {
			return;
		}
		// Scalar Fields
		this.setId(modification.getId());
		this.setCreateOn(modification.getCreateOn());
		this.setUpdateOn(modification.getUpdateOn());
		if (modification.getCreateBy() != null) {
			this.setCreateById(modification.getCreateBy().getId());
		}
		if (modification.getUpdateBy() != null) {
			this.setUpdateById(modification.getUpdateBy().getId());
		}
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

	public Calendar getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Calendar createOn) {
		this.createOn = createOn;
	}

	public Calendar getUpdateOn() {
		return updateOn;
	}

	public void setUpdateOn(Calendar updateOn) {
		this.updateOn = updateOn;
	}

	public long getCreateById() {
		return createById;
	}

	public void setCreateById(long createById) {
		this.createById = createById;
	}

	public long getUpdateById() {
		return updateById;
	}

	public void setUpdateById(long updateById) {
		this.updateById = updateById;
	}

}

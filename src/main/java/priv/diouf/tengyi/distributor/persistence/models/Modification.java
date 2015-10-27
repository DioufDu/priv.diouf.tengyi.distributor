package priv.diouf.tengyi.distributor.persistence.models;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import priv.diouf.tengyi.distributor.persistence.PersistenceConfig;
import priv.diouf.tengyi.distributor.persistence.models.account.Account;

@Entity
@Table(name = "T_MODIFICATION", schema = PersistenceConfig.PERSISTENCE_SCHEMA_NAME)
public class Modification implements Serializable {

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

	@Column(name = "CREATE_ON", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Calendar createOn;

	@Column(name = "UPDATE_ON", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Calendar updateOn;

	/*
	 * Navigations
	 */

	@ManyToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "CREATE_BY_ACCOUNT_ID")
	protected Account createBy;

	@ManyToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "UPDATE_BY_ACCOUNT_ID")
	protected Account updateBy;

	/*
	 * Constructors
	 */

	public Modification() {

	}

	public Modification(Account account) {
		if (this.getCreateOn() == null) {
			this.setCreateOn(Calendar.getInstance());
		}
		this.setUpdateOn(Calendar.getInstance());
		if (account != null) {
			if (this.getCreateBy() == null) {
				this.setCreateBy(account);
			}
			this.setUpdateBy(account);
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

	public Account getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Account createBy) {
		this.createBy = createBy;
	}

	public Account getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Account updateBy) {
		this.updateBy = updateBy;
	}

}

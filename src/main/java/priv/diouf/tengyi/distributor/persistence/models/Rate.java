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

@Entity
@Table(name = "T_RATE", schema = PersistenceConfig.PERSISTENCE_SCHEMA_NAME)
public class Rate implements Serializable {

	/**
	 * Generated Version UID
	 */
	private static final long serialVersionUID = 6536391738567709208L;

	/**
	 * ID
	 */

	@Id
	@GeneratedValue
	@Column(name = "ID")
	protected Long id;

	/*
	 * Scalar Fields
	 */

	@Column(name = "RATE_DATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	protected Calendar dateTime;

	@Column(name = "USER_ID")
	protected String userId;

	@Column(name = "TASTE")
	protected int taste;

	@Column(name = "STYLE")
	protected int style;

	@Column(name = "SERVICE")
	protected int service;

	/*
	 * Navigations
	 */

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false)
	@JoinColumn(name = "RATE_GROUP_ID")
	protected RateGroup rateGroup;

	/*
	 * Constructors
	 */

	public Rate() {

	}

	/*
	 * Properties
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Calendar getDateTime() {
		return dateTime;
	}

	public void setDateTime(Calendar date) {
		this.dateTime = date;
	}

	public int getTaste() {
		return taste;
	}

	public void setTaste(int taste) {
		this.taste = taste;
	}

	public int getStyle() {
		return style;
	}

	public void setStyle(int style) {
		this.style = style;
	}

	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	public RateGroup getRateGroup() {
		return rateGroup;
	}

	public void setRateGroup(RateGroup rateGroup) {
		this.rateGroup = rateGroup;
	}

}

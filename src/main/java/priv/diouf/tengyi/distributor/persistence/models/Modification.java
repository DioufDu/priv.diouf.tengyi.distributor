package priv.diouf.tengyi.distributor.persistence.models;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

@Embeddable
public class Modification implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 3621535655623853663L;

	/*
	 * Fields
	 */

	@Column(name = "CREATE_DATE_TIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Calendar createDateTime;

	@Column(name = "CREATE_USER_ID", nullable = true)
	protected String createUserId;

	@Column(name = "LAST_MODIFIED_DATE_TIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Calendar lastModifiedDateTime;

	@Column(name = "LAST_MODIFIED_USER_ID", nullable = true)
	protected String lastModifiedUserId;

	/*
	 * Navigations
	 */

	/*
	 * Constructors
	 */

	public Modification() {
		this.setCreateDateTime(Calendar.getInstance());
		this.setLastModifiedDateTime(Calendar.getInstance());
	}

	public Modification(String userId) {
		if (this.getCreateDateTime() == null) {
			this.setCreateDateTime(Calendar.getInstance());
		}
		this.setLastModifiedDateTime(Calendar.getInstance());
		if (StringUtils.isBlank(userId)) {
			if (StringUtils.isBlank(this.getCreateUserId())) {
				this.setCreateUserId(userId);
			}
			this.setLastModifiedUserId(userId);
		}
	}

	/*
	 * Properties
	 */

	public Calendar getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Calendar createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Calendar getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Calendar lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public String getLastModifiedUserId() {
		return lastModifiedUserId;
	}

	public void setLastModifiedUserId(String lastModifiedUserId) {
		this.lastModifiedUserId = lastModifiedUserId;
	}

}

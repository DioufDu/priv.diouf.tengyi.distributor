package priv.diouf.tengyi.distributor.persistence.models.account;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import priv.diouf.tengyi.distributor.persistence.PersistenceConfig;

@Entity
@Table(name = "T_CONTACT", schema = PersistenceConfig.PERSISTENCE_SCHEMA_NAME)
public class Contact implements Serializable {

	/**
	 * Generated Version UID
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
	 * Scalar Fields
	 */

	@Column(name = "TELEPHONE", length = 32)
	protected String telephone;

	@Column(name = "CELLPHONE", length = 32)
	protected String cellphone;

	@Column(name = "ALTERNATIVE_PHONE", length = 32)
	protected String alternativePhone;

	@Column(name = "FAX", length = 32)
	protected String fax;

	@Column(name = "EMAIL", length = 64)
	protected String email;

	/*
	 * Navigations
	 */

	@OneToOne(mappedBy = "contact", cascade = CascadeType.REFRESH, optional = false)
	protected Account account;

	/*
	 * Constructors
	 */

	public Contact() {

	}

	public Contact(Account account) {
		this.setAccount(account);
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getAlternativePhone() {
		return alternativePhone;
	}

	public void setAlternativePhone(String alternativePhone) {
		this.alternativePhone = alternativePhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}

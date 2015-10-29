package priv.diouf.tengyi.distributor.web.models.responses.account;

import java.io.Serializable;

import priv.diouf.tengyi.distributor.persistence.models.account.Contact;

public class ContactInfo implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Fields
	 */
	protected long id;

	protected String telephone;

	protected String cellphone;

	protected String alternativePhone;

	protected String fax;

	protected String email;

	/*
	 * Navigations
	 */

	/*
	 * Constructors
	 */

	public ContactInfo(Contact contact) {
		if (contact == null) {
			return;
		}
		this.setId(contact.getId());
		this.setTelephone(contact.getTelephone());
		this.setCellphone(contact.getMobile());
		this.setAlternativePhone(contact.getAlternativePhone());
		this.setFax(contact.getFax());
		this.setEmail(contact.getEmail());
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

}

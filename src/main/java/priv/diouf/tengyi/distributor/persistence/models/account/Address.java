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
@Table(name = "T_ADDRESS", schema = PersistenceConfig.PERSISTENCE_SCHEMA_NAME)
public class Address implements Serializable {

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

	@Column(name = "OVERALL")
	protected String overall;

	@Column(name = "COUNTRY")
	protected String country;

	@Column(name = "PROVINCE")
	protected String province;

	@Column(name = "CITY")
	protected String city;

	@Column(name = "ZONE")
	protected String zone;

	@Column(name = "ZIP")
	protected String zip;

	/*
	 * Navigations
	 */

	@OneToOne(mappedBy = "address", cascade = CascadeType.REFRESH, optional = false)
	protected Account account;

	/*
	 * Constructors
	 */

	public Address() {

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

	public String getOverall() {
		return overall;
	}

	public void setOverall(String overall) {
		this.overall = overall;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}

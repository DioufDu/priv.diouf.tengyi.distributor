package priv.diouf.tengyi.distributor.web.models.requests.product;

import java.io.Serializable;

public class PricingModelMergeRequest implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Fields
	 */

	protected long id;

	/*
	 * Scalar Fields
	 */

	protected String overall;

	protected String country;

	protected String province;

	protected String city;

	protected String zone;

	protected String zip;

	/*
	 * Navigations
	 */

	/*
	 * Constructors
	 */

	public PricingModelMergeRequest() {
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

}

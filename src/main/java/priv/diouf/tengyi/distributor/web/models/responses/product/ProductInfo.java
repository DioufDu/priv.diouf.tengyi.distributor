package priv.diouf.tengyi.distributor.web.models.responses.product;

import java.io.Serializable;

import priv.diouf.tengyi.distributor.common.models.enums.ProductStatus;
import priv.diouf.tengyi.distributor.persistence.models.product.Product;

public class ProductInfo implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Fields
	 */

	protected long id;

	public String name;

	public String serie;

	public String model;

	public String specification;

	public String comment;

	public ProductStatus status;

	/*
	 * Navigations
	 */

	protected PricingModelInfo pricingModel;

	/*
	 * Constructors
	 */

	public ProductInfo(Product product) {
		if (product == null) {
			return;
		}
		// Scalar Fields
		this.setId(product.getId());
		this.setName(product.getName());
		this.setSerie(product.getSerie());
		this.setModel(product.getModel());
		this.setSpecification(product.getSpecification());
		this.setComment(product.getComment());
		this.setStatus(product.getStatus());
		// Navigations
		this.setPricingModel(new PricingModelInfo(product.getPricingModel()));
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public PricingModelInfo getPricingModel() {
		return pricingModel;
	}

	public void setPricingModel(PricingModelInfo pricingModel) {
		this.pricingModel = pricingModel;
	}

}

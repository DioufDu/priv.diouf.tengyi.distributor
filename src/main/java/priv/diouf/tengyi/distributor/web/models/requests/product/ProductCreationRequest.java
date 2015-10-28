package priv.diouf.tengyi.distributor.web.models.requests.product;

import java.io.Serializable;

import priv.diouf.tengyi.distributor.common.models.enums.ProductStatus;
import priv.diouf.tengyi.distributor.web.models.requests.photo.PhotoGroupMergeRequest;

public class ProductCreationRequest implements Serializable {

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

	protected PricingModelMergeRequest pricingModel;

	protected PhotoGroupMergeRequest photo;

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

	public PricingModelMergeRequest getPricingModel() {
		return pricingModel;
	}

	public void setPricingModel(PricingModelMergeRequest pricingModel) {
		this.pricingModel = pricingModel;
	}

	public PhotoGroupMergeRequest getPhoto() {
		return photo;
	}

	public void setPhoto(PhotoGroupMergeRequest photo) {
		this.photo = photo;
	}

}

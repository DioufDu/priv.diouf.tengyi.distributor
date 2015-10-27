package priv.diouf.tengyi.distributor.persistence.models.product;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import priv.diouf.tengyi.distributor.common.models.enums.ProductStatus;
import priv.diouf.tengyi.distributor.persistence.PersistenceConfig;
import priv.diouf.tengyi.distributor.persistence.models.Modification;
import priv.diouf.tengyi.distributor.persistence.models.photo.ProductPhotoGroup;

@Entity
@Table(name = "T_PRODUCT", schema = PersistenceConfig.PERSISTENCE_SCHEMA_NAME)
public class Product implements Serializable {

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

	@Column(name = "NAME", length = 64)
	public String name;

	@Column(name = "SERIE", length = 64)
	public String serie;

	@Column(name = "MODEL", length = 64)
	public String model;

	@Column(name = "SPECIFICATION", length = 64)
	public String specification;

	@Column(name = "COMMENT", length = 512)
	public String comment;

	@Column(name = "STATUS", length = 16)
	public ProductStatus status;

	/*
	 * Embed
	 */

	/*
	 * Navigations
	 */

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "product")
	protected List<ProductPhotoGroup> productPhotoGroups;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
	@JoinColumn(name = "PRICING_MODEL_ID")
	protected PricingModel pricingModel;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
	@JoinColumn(name = "MODIFICATION_ID")
	protected Modification modification;

	/*
	 * Constructors
	 */

	public Product() {
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

	public List<ProductPhotoGroup> getProductPhotoGroups() {
		return productPhotoGroups;
	}

	public void setProductPhotoGroups(List<ProductPhotoGroup> productPhotoGroups) {
		this.productPhotoGroups = productPhotoGroups;
	}

	public PricingModel getPricingModel() {
		return pricingModel;
	}

	public void setPricingModel(PricingModel pricingModel) {
		this.pricingModel = pricingModel;
	}

	public Modification getModification() {
		return modification;
	}

	public void setModification(Modification modification) {
		this.modification = modification;
	}

}

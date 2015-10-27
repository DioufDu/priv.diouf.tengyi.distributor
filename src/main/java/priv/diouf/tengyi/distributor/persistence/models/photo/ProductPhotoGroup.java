package priv.diouf.tengyi.distributor.persistence.models.photo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import priv.diouf.tengyi.distributor.persistence.models.product.Product;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("PRODUCT")
public class ProductPhotoGroup extends PhotoGroup implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Navigations
	 */

	@ManyToOne(cascade = CascadeType.REFRESH, optional = true)
	@JoinColumn(name = "PRODUCT_ID")
	protected Product product;

	/*
	 * Constructors
	 */

	public ProductPhotoGroup() {

	}

	public ProductPhotoGroup(Product product) {
		this.setProduct(product);
	}

	/*
	 * Properties
	 */

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}

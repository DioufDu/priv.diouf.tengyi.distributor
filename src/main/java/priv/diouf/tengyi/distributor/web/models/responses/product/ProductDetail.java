package priv.diouf.tengyi.distributor.web.models.responses.product;

import java.io.Serializable;

import priv.diouf.tengyi.distributor.persistence.models.product.Product;
import priv.diouf.tengyi.distributor.web.models.responses.ModificationDetail;

public class ProductDetail extends ProductInfo implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Fields
	 */

	/*
	 * Navigations
	 */

	protected ModificationDetail modification;

	/*
	 * Constructors
	 */

	public ProductDetail(Product product) {
		super(product);
		if (product == null) {
			return;
		}
		// Scalar Fields
		// Navigations
		this.setModification(new ModificationDetail(product.getModification()));
	}

	/*
	 * Properties
	 */

	public ModificationDetail getModification() {
		return modification;
	}

	public void setModification(ModificationDetail modification) {
		this.modification = modification;
	}
}

package priv.diouf.tengyi.distributor.web.models.responses.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.util.CollectionUtils;

import priv.diouf.tengyi.distributor.persistence.models.product.Product;

public class ProductList extends ArrayList<ProductDetail> implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -9142298842946763771L;

	/*
	 * Constructors
	 */

	public ProductList(Collection<Product> products) {
		super();
		if (!CollectionUtils.isEmpty(products)) {
			for (Product product : products) {
				this.add(new ProductDetail(product));
			}
		}
	}

}

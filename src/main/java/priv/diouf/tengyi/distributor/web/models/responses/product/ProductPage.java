package priv.diouf.tengyi.distributor.web.models.responses.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import priv.diouf.tengyi.distributor.persistence.models.product.Product;

public class ProductPage extends PageImpl<ProductDetail> implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -9142298842946763771L;

	/*
	 * Constructors
	 */

	public ProductPage(List<ProductDetail> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

	/*
	 * Actions
	 */

	public static ArrayList<ProductDetail> createDetails(Collection<Product> products) {
		ArrayList<ProductDetail> items = null;
		if (CollectionUtils.isEmpty(products)) {
			items = new ArrayList<ProductDetail>(1);
		} else {
			items = new ArrayList<ProductDetail>(products.size());
			for (Product product : products) {
				items.add(new ProductDetail(product));
			}
		}
		return items;
	}
}

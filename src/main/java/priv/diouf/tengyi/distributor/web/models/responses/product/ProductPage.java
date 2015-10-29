package priv.diouf.tengyi.distributor.web.models.responses.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import priv.diouf.tengyi.distributor.persistence.models.product.Product;

public class ProductPage<TProductModel> extends PageImpl<TProductModel> implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Constructors
	 */

	public ProductPage(Collection<Product> content, Helper<TProductModel> helper, Pageable pageable, long total) {
		super(createModels(content, helper), pageable, total);
	}

	/*
	 * Actions
	 */

	public static <TProductModel> ArrayList<TProductModel> createModels(Collection<Product> products, Helper<TProductModel> helper) {
		ArrayList<TProductModel> items = null;
		if (CollectionUtils.isEmpty(products)) {
			items = new ArrayList<TProductModel>(1);
		} else {
			items = new ArrayList<TProductModel>(products.size());
			for (Product product : products) {
				items.add(helper.createModel(product));
			}
		}
		return items;
	}

	public static abstract class Helper<TProductModel> {
		protected abstract TProductModel createModel(Product product);
	}
}

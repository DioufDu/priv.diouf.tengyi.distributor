package priv.diouf.tengyi.distributor.web.models.responses.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import priv.diouf.tengyi.distributor.persistence.models.product.Product;

public abstract class ProductGroup<TGroup extends ProductGroup.Group<TGroupKey, TProductModel>, TGroupKey extends Serializable, TProductModel extends Serializable> extends TreeSet<TGroup> implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Constructors
	 */

	public ProductGroup(Collection<Product> products, Comparator<TGroup> comparator) {
		super(comparator);
		if (CollectionUtils.isEmpty(products)) {
			return;
		}
		for (Product product : products) {
			TGroupKey key = this.getKey(product);
			TGroup group = null;
			for (TGroup group0 : this) {
				if (group0.getKey().equals(key)) {
					group = group0;
					break;
				}
			}
			if (group == null) {
				group = this.createGroup(this.getKey(product));
				this.add(group);
			}
			group.getItems().add(this.createModel(product));
		}
	}

	/*
	 * Actions
	 */

	protected abstract TGroupKey getKey(Product product);

	protected abstract TGroup createGroup(TGroupKey key);

	protected abstract TProductModel createModel(Product product);

	/*
	 * Group Template
	 */

	public static class Group<TGroupKey extends Serializable, TProductModel extends Serializable> implements Serializable {

		/**
		 * Generated Serial Version UID
		 */
		private static final long serialVersionUID = 1L;

		/*
		 * Fields
		 */

		@JsonProperty("key")
		protected TGroupKey key;

		@JsonProperty("items")
		protected List<TProductModel> items;

		/*
		 * Constructor
		 */

		public Group(TGroupKey key) {
			this.setKey(key);
			this.items = new ArrayList<TProductModel>();
		}

		/*
		 * Actions
		 */

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object obj) {
			if (this.getKey() != null && obj != null && obj instanceof Group) {
				return this.getKey().equals(((Group<TGroupKey, TProductModel>) obj).getKey());
			}
			return false;
		}

		@Override
		public int hashCode() {
			if (this.getKey() != null) {
				return this.getKey().hashCode();
			}
			return this.hashCode();
		}

		/*
		 * Properties
		 */

		public TGroupKey getKey() {
			return key;
		}

		public void setKey(TGroupKey key) {
			this.key = key;
		}

		public List<TProductModel> getItems() {
			return items;
		}

		public void setItems(List<TProductModel> items) {
			this.items = items;
		}
	}
}

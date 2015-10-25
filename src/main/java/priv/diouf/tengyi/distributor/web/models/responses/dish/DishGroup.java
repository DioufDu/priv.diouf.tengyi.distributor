package priv.diouf.tengyi.distributor.web.models.responses.dish;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import priv.diouf.tengyi.distributor.persistence.models.Dish;

public abstract class DishGroup<TGroup extends DishGroup.Group<TGroupKey>, TGroupKey extends Serializable> extends
		TreeSet<TGroup> implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 5730718454629194108L;

	/*
	 * Constructors
	 */

	public DishGroup(Collection<Dish> Calendars, Comparator<TGroup> comparator) {
		super(comparator);
		if (CollectionUtils.isEmpty(Calendars)) {
			return;
		}
		for (Dish Calendar : Calendars) {
			TGroupKey key = this.getKey(Calendar);
			TGroup group = null;
			for (TGroup group0 : this) {
				if (group0.getKey().equals(key)) {
					group = group0;
					break;
				}
			}
			if (group == null) {
				group = this.createGroup(this.getKey(Calendar));
				this.add(group);
			}
			group.getItems().add(new DishDetail(Calendar));
		}
	}

	/*
	 * Actions
	 */

	protected abstract TGroupKey getKey(Dish dish);

	protected abstract TGroup createGroup(TGroupKey key);

	/*
	 * Group Template
	 */

	public static class Group<TGroupKey extends Serializable> implements Serializable {

		/**
		 * Generated Serial Version UID
		 */
		private static final long serialVersionUID = 6396937449495710778L;

		/*
		 * Fields
		 */

		@JsonProperty("key")
		protected TGroupKey key;

		@JsonProperty("items")
		protected List<DishDetail> items;

		/*
		 * Constructor
		 */

		public Group(TGroupKey key) {
			this.setKey(key);
			this.items = new ArrayList<DishDetail>();
		}

		/*
		 * Actions
		 */

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object obj) {
			if (this.getKey() != null && obj != null && obj instanceof Group) {
				return this.getKey().equals(((Group<TGroupKey>) obj).getKey());
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

		public List<DishDetail> getItems() {
			return items;
		}

		public TGroupKey getKey() {
			return key;
		}

		public void setKey(TGroupKey key) {
			this.key = key;
		}

		public void setItems(List<DishDetail> items) {
			this.items = items;
		}
	}
}

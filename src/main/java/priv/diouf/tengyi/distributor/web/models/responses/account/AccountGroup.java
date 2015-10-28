package priv.diouf.tengyi.distributor.web.models.responses.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import priv.diouf.tengyi.distributor.persistence.models.account.Account;

public abstract class AccountGroup<TGroup extends AccountGroup.Group<TGroupKey, TAccountModel>, TGroupKey extends Serializable, TAccountModel extends Serializable> extends TreeSet<TGroup> implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Constructors
	 */

	public AccountGroup(Collection<Account> accounts, Comparator<TGroup> comparator) {
		super(comparator);
		if (CollectionUtils.isEmpty(accounts)) {
			return;
		}
		for (Account account : accounts) {
			TGroupKey key = this.getKey(account);
			TGroup group = null;
			for (TGroup group0 : this) {
				if (group0.getKey().equals(key)) {
					group = group0;
					break;
				}
			}
			if (group == null) {
				group = this.createGroup(this.getKey(account));
				this.add(group);
			}
			group.getItems().add(this.createModel(account));
		}
	}

	/*
	 * Actions
	 */

	protected abstract TGroupKey getKey(Account dish);

	protected abstract TGroup createGroup(TGroupKey key);

	protected abstract TAccountModel createModel(Account account);

	/*
	 * Group Template
	 */

	public static class Group<TGroupKey extends Serializable, TAccountModel extends Serializable> implements Serializable {

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
		protected List<TAccountModel> items;

		/*
		 * Constructor
		 */

		public Group(TGroupKey key) {
			this.setKey(key);
			this.items = new ArrayList<TAccountModel>();
		}

		/*
		 * Actions
		 */

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object obj) {
			if (this.getKey() != null && obj != null && obj instanceof Group) {
				return this.getKey().equals(((Group<TGroupKey, TAccountModel>) obj).getKey());
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

		public List<TAccountModel> getItems() {
			return items;
		}

		public void setItems(List<TAccountModel> items) {
			this.items = items;
		}
	}
}

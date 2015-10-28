package priv.diouf.tengyi.distributor.web.models.responses.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import priv.diouf.tengyi.distributor.common.models.enums.AccountType;

public class AccountStatistics extends ArrayList<AccountStatistics.Item> implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Actions
	 */

	public AccountStatistics(Map<AccountType, Long> groups) {
		for (Entry<AccountType, Long> group : groups.entrySet()) {
			if (group == null) {
				continue;
			}
			this.add(new Item(group));
		}
	}

	/*
	 * Item Template
	 */

	public static class Item implements Serializable {

		/**
		 * Generated Serial Version UID
		 */
		private static final long serialVersionUID = -8563458439753638956L;

		/*
		 * Fields
		 */

		protected AccountType type;

		protected Long count;

		/*
		 * Constructors
		 */

		public Item(Entry<AccountType, Long> groupItem) {
			this.setType(groupItem.getKey());
			this.setCount(groupItem.getValue());
		}

		/*
		 * Properties
		 */

		public AccountType getType() {
			return type;
		}

		public void setType(AccountType type) {
			this.type = type;
		}

		public Long getCount() {
			return count;
		}

		public void setCount(Long count) {
			this.count = count;
		}

	}
}

package priv.diouf.tengyi.distributor.web.models.responses.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class ProductStatistics extends ArrayList<ProductStatistics.Item> implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Actions
	 */

	public ProductStatistics(Map<String, Long> groups) {
		for (Entry<String, Long> group : groups.entrySet()) {
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
		private static final long serialVersionUID = 1L;

		/*
		 * Fields
		 */

		protected String serie;

		protected Long count;

		/*
		 * Constructors
		 */

		public Item(Entry<String, Long> dishGroupByLine) {
			this.setSerie(dishGroupByLine.getKey());
			this.setCount(dishGroupByLine.getValue());
		}

		/*
		 * Properties
		 */

		public String getSerie() {
			return serie;
		}

		public void setSerie(String serie) {
			this.serie = serie;
		}

		public Long getCount() {
			return count;
		}

		public void setCount(Long count) {
			this.count = count;
		}

	}
}

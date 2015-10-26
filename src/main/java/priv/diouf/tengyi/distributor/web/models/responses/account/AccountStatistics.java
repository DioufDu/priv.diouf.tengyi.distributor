package priv.diouf.tengyi.distributor.web.models.responses.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountStatistics extends ArrayList<AccountStatistics.Item> implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -323593314600559214L;

	/*
	 * Actions
	 */

	public AccountStatistics(Map<Calendar, long[]> dishGroupsByLine) {
		for (Entry<Calendar, long[]> dishGroupByLine : dishGroupsByLine.entrySet()) {
			if (dishGroupByLine == null) {
				continue;
			}
			this.add(new Item(dishGroupByLine));
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

		@JsonProperty("offerDate")
		protected Calendar offerDate;

		@JsonProperty("dishCount")
		protected long dishCount;

		@JsonProperty("recommendationCount")
		protected long recommendationCount;

		/*
		 * Constructors
		 */

		public Item(Entry<Calendar, long[]> dishGroupByLine) {
			this.setOfferDate(dishGroupByLine.getKey());
			this.setDishCount((long) dishGroupByLine.getValue()[0]);
			this.setRecommendationCount((long) dishGroupByLine.getValue()[1]);
		}

		/*
		 * Properties
		 */

		public Calendar getOfferDate() {
			return offerDate;
		}

		public void setOfferDate(Calendar offerDate) {
			this.offerDate = offerDate;
		}

		public long getDishCount() {
			return dishCount;
		}

		public void setDishCount(long dishCount) {
			this.dishCount = dishCount;
		}

		public long getRecommendationCount() {
			return recommendationCount;
		}

		public void setRecommendationCount(long recommendationCount) {
			this.recommendationCount = recommendationCount;
		}

	}
}

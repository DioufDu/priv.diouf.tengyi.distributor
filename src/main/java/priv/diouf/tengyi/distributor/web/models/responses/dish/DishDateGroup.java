package priv.diouf.tengyi.distributor.web.models.responses.dish;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;

import priv.diouf.tengyi.distributor.persistence.models.Dish;

public class DishDateGroup extends DishGroup<DishGroup.Group<Calendar>, Calendar> implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -1443720464453666210L;

	/*
	 * Fields
	 */

	/*
	 * Constructors
	 */

	public DishDateGroup(Collection<Dish> dishes) {
		super(dishes, new Comparator<Group<Calendar>>() {
			@Override
			public int compare(DishGroup.Group<Calendar> o1, DishGroup.Group<Calendar> o2) {
				if (o1 == null || o2 == null) {
					return -1;
				} else {
					return o1.getKey().compareTo(o2.getKey());
				}
			}
		});
	}

	/*
	 * Actions
	 */

	@Override
	public Calendar getKey(Dish dish) {
		return dish.getOfferDate();
	}

	@Override
	protected DishGroup.Group<Calendar> createGroup(Calendar key) {
		return new DishGroup.Group<Calendar>(key);
	}

}

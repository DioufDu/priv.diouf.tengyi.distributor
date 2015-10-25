package priv.diouf.tengyi.distributor.web.models.responses.dish;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;

import priv.diouf.tengyi.distributor.persistence.models.Dish;

public class DishLineGroup extends DishGroup<DishGroup.Group<String>, String> implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -1443720464453666210L;

	/*
	 * Constructors
	 */

	public DishLineGroup(Collection<Dish> dishs) {
		super(dishs, new Comparator<Group<String>>() {
			@Override
			public int compare(DishGroup.Group<String> o1, DishGroup.Group<String> o2) {
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
	public String getKey(Dish dish) {
		return dish.getLine();
	}

	@Override
	protected DishGroup.Group<String> createGroup(String key) {
		return new DishGroup.Group<String>(key);
	}
}

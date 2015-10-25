package priv.diouf.tengyi.distributor.web.models.responses.dish;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.util.CollectionUtils;

import priv.diouf.tengyi.distributor.persistence.models.Dish;

public class DishList extends ArrayList<DishDetail> implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -9142298842946763771L;

	/*
	 * Constructors
	 */

	public DishList(Collection<Dish> dishs) {
		super();
		if (!CollectionUtils.isEmpty(dishs)) {
			for (Dish dish : dishs) {
				this.add(new DishDetail(dish));
			}
		}
	}

}

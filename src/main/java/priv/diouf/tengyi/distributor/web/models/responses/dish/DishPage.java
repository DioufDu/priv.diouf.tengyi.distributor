package priv.diouf.tengyi.distributor.web.models.responses.dish;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import priv.diouf.tengyi.distributor.persistence.models.Dish;

public class DishPage extends PageImpl<DishDetail> implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -9142298842946763771L;

	/*
	 * Constructors
	 */

	public DishPage(List<DishDetail> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

	/*
	 * Actions
	 */

	public static ArrayList<DishDetail> createDishDetails(Collection<Dish> dishes) {
		ArrayList<DishDetail> items = null;
		if (CollectionUtils.isEmpty(dishes)) {
			items = new ArrayList<DishDetail>(1);
		} else {
			items = new ArrayList<DishDetail>(dishes.size());
			for (Dish dish : dishes) {
				items.add(new DishDetail(dish));
			}
		}
		return items;
	}
}

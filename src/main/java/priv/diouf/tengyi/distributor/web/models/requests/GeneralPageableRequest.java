package priv.diouf.tengyi.distributor.web.models.requests;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GeneralPageableRequest<TCriteria> {

	/*
	 * Constants
	 */

	protected static final int DEFAULT_PAGE_INDEX = 0;
	protected static final int DEFAULT_PAGE_SIZE = 12;

	/*
	 * Fields
	 */

	protected Integer index = DEFAULT_PAGE_INDEX;
	protected Integer size = DEFAULT_PAGE_SIZE;
	protected Map<String, Direction> sorts;
	protected TCriteria criteria;

	@JsonIgnore
	protected PageRequest pageRequest;

	/*
	 * Constructors
	 */

	/*
	 * Actions
	 */

	public PageRequest getPageRequest() {
		if (pageRequest == null) {
			if (!CollectionUtils.isEmpty(sorts)) {
				Order[] orders = new Order[sorts.size()];
				int idx = 0;
				for (Entry<String, Direction> sort : sorts.entrySet()) {
					orders[idx++] = new Order(sort.getValue() == null ? Direction.ASC : sort.getValue(), sort.getKey());
				}
				pageRequest = new PageRequest(this.getIndex(), this.getSize(), new Sort(orders));
			} else {
				pageRequest = new PageRequest(this.getIndex(), this.getSize());
			}

		}
		return pageRequest;
	}

	/*
	 * Properties
	 */

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Map<String, Direction> getSorts() {
		return sorts;
	}

	public void setSorts(Map<String, Direction> sorts) {
		this.sorts = sorts;
	}

	public TCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(TCriteria criteria) {
		this.criteria = criteria;
	}

}

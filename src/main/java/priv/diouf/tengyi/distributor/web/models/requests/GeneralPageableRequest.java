package priv.diouf.tengyi.distributor.web.models.requests;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import priv.diouf.tengyi.distributor.persistence.models.account.Account_;

public class GeneralPageableRequest<TCriteria> {

	/*
	 * Fields
	 */

	protected static final int DEFAULT_PAGE_INDEX = 0;
	protected static final int DEFAULT_PAGE_SIZE = 30;

	private final Sort DEFAULT_SORT = new Sort(Direction.DESC, Account_.type.getName(), Account_.status.getName(), Account_.name.getName(),
			Account_.loginId.getName(), Account_.title.getName());

	protected Integer pageIndex = DEFAULT_PAGE_INDEX;
	protected Integer pageSize = DEFAULT_PAGE_SIZE;

	protected Direction sortDirection;
	protected String[] sortFields;

	protected PageRequest pageRequest;
	protected TCriteria criteria;

	/*
	 * Constructors
	 */

	/*
	 * Properties
	 */

	public TCriteria getCriteria() {
		return this.criteria;
	}

	public void setCriteria(TCriteria criteria) {
		this.criteria = criteria;
	}

	public PageRequest getPageRequest() {
		if (pageRequest == null) {
			pageRequest = new PageRequest(this.getPageIndex(), this.getPageSize(), DEFAULT_SORT);
		}
		return pageRequest;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		if (pageIndex == null || pageIndex == 0) {
			this.pageIndex = DEFAULT_PAGE_INDEX;
		} else {
			this.pageIndex = pageIndex;
		}
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize == null || pageSize == 0) {
			this.pageSize = DEFAULT_PAGE_SIZE;
		} else {
			this.pageSize = pageSize;
		}
	}

	public void setSortDirection(Direction sortDirection) {
		this.sortDirection = sortDirection;
	}
}

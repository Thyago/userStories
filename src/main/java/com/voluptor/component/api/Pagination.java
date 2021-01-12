package com.voluptor.component.api;

public class Pagination {
	public Pagination(Integer offset, Integer limit, Long total)
	{
		this.offset = offset;
		this.limit = limit;
		this.total = total;
	}
	
	Integer offset;
	Integer limit;
	Long total;
	
	public Integer getOffset() {
		return offset;
	}
	public Integer getLimit() {
		return limit;
	}
	public Long getTotal() {
		return total;
	}	
}

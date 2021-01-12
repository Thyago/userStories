package com.voluptor.component.api.response;

import java.util.List;

import com.voluptor.component.api.Pagination;

public abstract class ListResponse<T> {
	
	public ListResponse(List<T> data) {
		this.setData(data);
	}
	
	public ListResponse(List<T> data, Pagination pagination) {
		this(data);
		this.pagination = pagination;
	}

	List<T> data;
	
	Pagination pagination;
	
	
	public List<T> getData() {
		return data;
	}

	private void setData(List<T> data) {
		this.data = data;
	}
	
	public Pagination getPagination() {
		return pagination;
	}
}

package com.voluptor.userStories.dto.customerProject;

import java.util.List;

import com.voluptor.component.api.Pagination;
import com.voluptor.component.api.response.ListResponse;

public class ListCustomerProjectResponse extends ListResponse<GetCustomerProjectResponse> {

	public ListCustomerProjectResponse(List<GetCustomerProjectResponse> data, Pagination pagination) {
		super(data, pagination);
	}
	
}

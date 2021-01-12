package com.voluptor.userStories.dto.userType;

import java.util.List;

import com.voluptor.component.api.Pagination;
import com.voluptor.component.api.response.ListResponse;

public class ListUserTypeResponse extends ListResponse<GetUserTypeResponse> {

	public ListUserTypeResponse(List<GetUserTypeResponse> data, Pagination pagination) {
		super(data, pagination);
	}
}

package com.voluptor.userStories.dto.project;

import java.util.List;

import com.voluptor.component.api.Pagination;
import com.voluptor.component.api.response.ListResponse;

public class ListProjectResponse extends ListResponse<GetProjectResponse> {

	public ListProjectResponse(List<GetProjectResponse> data, Pagination pagination) {
		super(data, pagination);
	}
}

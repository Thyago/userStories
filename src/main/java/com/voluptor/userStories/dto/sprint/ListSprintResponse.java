package com.voluptor.userStories.dto.sprint;

import java.util.List;

import com.voluptor.component.api.Pagination;
import com.voluptor.component.api.response.ListResponse;

public class ListSprintResponse extends ListResponse<GetSprintResponse> {

	public ListSprintResponse(List<GetSprintResponse> data, Pagination pagination) {
		super(data, pagination);
	}
}

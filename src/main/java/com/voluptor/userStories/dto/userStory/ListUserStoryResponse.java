package com.voluptor.userStories.dto.userStory;

import java.util.List;

import com.voluptor.component.api.Pagination;
import com.voluptor.component.api.response.ListResponse;

public class ListUserStoryResponse extends ListResponse<GetUserStoryResponse> {

	public ListUserStoryResponse(List<GetUserStoryResponse> data, Pagination pagination) {
		super(data, pagination);
	}
}

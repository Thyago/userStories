package com.voluptor.userStories.dto.userStory;

import java.util.List;

import com.voluptor.component.api.Pagination;
import com.voluptor.component.api.response.ListResponse;

public class ListUserStoryVersionResponse extends ListResponse<GetUserStoryVersionResponse> {

	public ListUserStoryVersionResponse(List<GetUserStoryVersionResponse> data, Pagination pagination) {
		super(data, pagination);
	}
}

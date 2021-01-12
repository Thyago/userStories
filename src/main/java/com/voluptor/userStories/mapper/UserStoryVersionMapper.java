package com.voluptor.userStories.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.voluptor.userStories.dto.userStory.GetUserStoryVersionResponse;
import com.voluptor.userStories.model.UserStory;
import com.voluptor.userStories.model.UserStoryVersion;

@Mapper(componentModel = "cdi")
public abstract class UserStoryVersionMapper {
	
	@Mapping(ignore = true, target = "id")
	public abstract UserStoryVersion from(UserStory userStory);
	
	public abstract List<GetUserStoryVersionResponse> toListResponses(List<UserStoryVersion> objects);
	
	@Mapping(source = "userStory.id", target = "userStoryId", resultType = Long.class)
	public abstract GetUserStoryVersionResponse toGetResponse(UserStoryVersion object);

}

package com.voluptor.userStories.mapper;

import java.util.List;

import javax.inject.Inject;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.voluptor.userStories.dto.userStory.CreateUserStoryRequest;
import com.voluptor.userStories.dto.userStory.GetUserStoryResponse;
import com.voluptor.userStories.dto.userStory.UpdateUserStoryRequest;
import com.voluptor.userStories.model.Sprint;
import com.voluptor.userStories.model.UserStory;
import com.voluptor.userStories.model.UserType;
import com.voluptor.userStories.repository.SprintRepository;
import com.voluptor.userStories.repository.UserTypeRepository;

@Mapper(componentModel = "cdi")
public abstract class UserStoryMapper {
	
	@Inject
	SprintRepository sprintRepository;
	
	@Inject
	UserTypeRepository userTypeRepository;
	
	@Mapping(source = "sprintId", target = "sprint")
	@Mapping(source = "userTypeId", target = "userType")
	public abstract UserStory from(CreateUserStoryRequest request);

	@Mapping(source = "sprintId", target = "sprint")
	@Mapping(source = "userTypeId", target = "userType")
	public abstract void updateFrom(UpdateUserStoryRequest request, @MappingTarget UserStory object);
		
	public abstract List<GetUserStoryResponse> toListResponses(List<UserStory> objects);
	
	@Mapping(source = "project.id", target = "projectId", resultType = Long.class)
	@Mapping(source = "sprint.id", target="sprintId", resultType = Long.class)
	@Mapping(source = "userType.id", target="userTypeId", resultType = Long.class)
	public abstract GetUserStoryResponse toGetResponse(UserStory object);
	
	public Sprint sprintFromId(Long id) {
		if (id == null) {
			return null;
		}
		return sprintRepository.findById(id);
	}
	
	public UserType userTypeFromId(Long id) {
		if (id == null) {
			return null;
		}
		return userTypeRepository.findById(id);
	}
}

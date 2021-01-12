package com.voluptor.userStories.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.voluptor.userStories.dto.sprint.CreateSprintRequest;
import com.voluptor.userStories.dto.sprint.GetSprintResponse;
import com.voluptor.userStories.dto.sprint.UpdateSprintRequest;
import com.voluptor.userStories.model.Sprint;

@Mapper(componentModel = "cdi")
public abstract class SprintMapper {
	
	public abstract Sprint from(CreateSprintRequest request);

	public abstract void updateFrom(UpdateSprintRequest request, @MappingTarget Sprint object);
		
	public abstract List<GetSprintResponse> toListResponses(List<Sprint> objects);
	
	@Mapping(source = "project.id", target = "projectId", resultType = Long.class)
	public abstract GetSprintResponse toGetResponse(Sprint object);
}

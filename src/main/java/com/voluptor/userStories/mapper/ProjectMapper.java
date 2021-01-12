package com.voluptor.userStories.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.voluptor.userStories.dto.project.CreateProjectRequest;
import com.voluptor.userStories.dto.project.GetProjectResponse;
import com.voluptor.userStories.dto.project.UpdateProjectRequest;
import com.voluptor.userStories.model.Project;

@Mapper(componentModel = "cdi")
public abstract class ProjectMapper {

	public abstract Project from(CreateProjectRequest request);

	public abstract void updateFrom(UpdateProjectRequest request, @MappingTarget Project object);
		
	public abstract List<GetProjectResponse> toListResponses(List<Project> objects);

	public abstract GetProjectResponse toGetResponse(Project object);
}

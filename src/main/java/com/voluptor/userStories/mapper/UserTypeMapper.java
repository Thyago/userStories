package com.voluptor.userStories.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.voluptor.userStories.dto.userType.CreateUserTypeRequest;
import com.voluptor.userStories.dto.userType.GetUserTypeResponse;
import com.voluptor.userStories.dto.userType.UpdateUserTypeRequest;
import com.voluptor.userStories.model.UserType;

@Mapper(componentModel = "cdi")
public abstract class UserTypeMapper {

	public abstract UserType from(CreateUserTypeRequest request);

	public abstract void updateFrom(UpdateUserTypeRequest request, @MappingTarget UserType object);
		
	public abstract List<GetUserTypeResponse> toListResponses(List<UserType> objects);
	
	@Mapping(source = "project.id", target = "projectId", resultType = Long.class)
	public abstract GetUserTypeResponse toGetResponse(UserType object);
}

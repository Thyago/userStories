package com.voluptor.userStories.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.voluptor.userStories.dto.customerProject.GetCustomerProjectResponse;
import com.voluptor.userStories.model.CustomerProject;

@Mapper(componentModel = "cdi")
public abstract class CustomerProjectMapper {
	
	public abstract List<GetCustomerProjectResponse> toListResponses(List<CustomerProject> objects);
	
	@Mapping(source = "customer.id", target = "customerId", resultType = Long.class)
	@Mapping(source = "project.id", target = "projectId", resultType = Long.class)
	public abstract GetCustomerProjectResponse toGetResponse(CustomerProject object);
}

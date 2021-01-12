package com.voluptor.userStories.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import com.voluptor.component.api.Pagination;
import com.voluptor.userStories.dto.userType.CreateUserTypeRequest;
import com.voluptor.userStories.dto.userType.ListUserTypeFilters;
import com.voluptor.userStories.dto.userType.ListUserTypeResponse;
import com.voluptor.userStories.dto.userType.UpdateUserTypeRequest;
import com.voluptor.userStories.mapper.UserTypeMapper;
import com.voluptor.userStories.model.Project;
import com.voluptor.userStories.model.UserType;
import com.voluptor.userStories.repository.UserTypeRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

@ApplicationScoped
public class UserTypeService {

	@Inject
	UserTypeRepository repository;
	
	@Inject
	UserTypeMapper mapper;
	
	@Inject
	ProjectService projectService;
	
	public ListUserTypeResponse list(ListUserTypeFilters listUserTypeFilters, Integer offset, Integer limit)
	{
		PanacheQuery<UserType> query = repository.find(listUserTypeFilters);
		List<UserType> userTypes = query.range(offset, offset+limit-1).list();
		return new ListUserTypeResponse(mapper.toListResponses(userTypes), new Pagination(offset, limit, query.count()));		
	}
	
	public UserType get(Long projectId, Long id) throws NotFoundException
	{
		UserType userType = repository.findByProjectIdId(projectId, id);
		if (userType == null) {
			throw new NotFoundException();
		}
		return userType;
	}
	
	@Transactional
	public UserType create(Long projectId, CreateUserTypeRequest createUserTypeRequest)
	{
		Project project = projectService.get(projectId);
						
		UserType userType = mapper.from(createUserTypeRequest);		
		userType.setProject(project);

		repository.persist(userType);

		return userType;
	}
	
	@Transactional
	public void update(UserType userType, UpdateUserTypeRequest updateUserTypeRequest)
	{
		mapper.updateFrom(updateUserTypeRequest, userType);
		repository.persist(userType);
	}
	
	@Transactional
	public void delete(UserType userType)
	{
		repository.delete(userType);
	}
}

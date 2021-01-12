package com.voluptor.userStories.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import com.voluptor.component.api.Pagination;
import com.voluptor.component.archivable.exception.ArchivedException;
import com.voluptor.component.archivable.exception.UnarchivedException;
import com.voluptor.component.archivable.service.ArchivableService;
import com.voluptor.userStories.dto.userStory.CreateUserStoryRequest;
import com.voluptor.userStories.dto.userStory.ListUserStoryFilters;
import com.voluptor.userStories.dto.userStory.ListUserStoryResponse;
import com.voluptor.userStories.dto.userStory.UpdateUserStoryRequest;
import com.voluptor.userStories.mapper.UserStoryMapper;
import com.voluptor.userStories.model.Project;
import com.voluptor.userStories.model.UserStory;
import com.voluptor.userStories.model.UserStoryVersion;
import com.voluptor.userStories.repository.UserStoryRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

@ApplicationScoped
public class UserStoryService implements ArchivableService<UserStory> {

	@Inject
	UserStoryRepository repository;
	
	@Inject
	UserStoryMapper mapper;
	
	@Inject
	ProjectService projectService;
	
	@Inject
	UserStoryVersionService versionService;
	
	public ListUserStoryResponse list(ListUserStoryFilters listUserStoryFilters, Integer offset, Integer limit)
	{
		PanacheQuery<UserStory> query = repository.find(listUserStoryFilters);
		List<UserStory> userStories = query.range(offset, offset+limit-1).list();
		return new ListUserStoryResponse(mapper.toListResponses(userStories), new Pagination(offset, limit, query.count()));		
	}
	
	public UserStory get(Long projectId, Long id) throws NotFoundException
	{
		UserStory userStory = repository.findByProjectIdId(projectId, id);
		if (userStory == null) {
			throw new NotFoundException();
		}
		return userStory;
	}
	
	@Transactional
	public UserStory create(Long projectId, CreateUserStoryRequest createUserStoryRequest)
	{
		Project project = projectService.get(projectId);
						
		UserStory userStory = mapper.from(createUserStoryRequest);		
		userStory.setProject(project);

		repository.persist(userStory);

		return userStory;
	}
	
	@Transactional
	public void update(UserStory userStory, UpdateUserStoryRequest updateUserStoryRequest)
	{
		if (updateUserStoryRequest.getIncrementVersion()) {
			versionService.generateVersion(userStory);
			userStory.setVersion(userStory.getVersion() + 1);			
		}
		
		mapper.updateFrom(updateUserStoryRequest, userStory);
		
		repository.persist(userStory);
	}
	
	@Transactional
	public void delete(UserStory userStory)
	{
		repository.delete(userStory);
	}

	public UserStory restore(UserStoryVersion userStoryVersion)
	{
		UserStory userStory = userStoryVersion.getUserStory();
		userStory.setUserType(userStoryVersion.getUserType());
		userStory.setWhoDescription(userStoryVersion.getWhoDescription());
		userStory.setWhat(userStoryVersion.getWhat());
		userStory.setWhy(userStoryVersion.getWhy());
		userStory.setAcceptanceCriterias(userStoryVersion.getAcceptanceCriterias());
		userStory.setSize(userStoryVersion.getSize());
		userStory.setBusinessValue(userStoryVersion.getBusinessValue());			
		userStory.setIsArchived(false);
		repository.persist(userStory);
		
		return userStory;
	}
	
	public void archive(UserStory userStory) throws ArchivedException
	{
		userStory.archive();
		repository.persist(userStory);
	}
	
	public void unarchive(UserStory userStory) throws UnarchivedException
	{
		userStory.unarchive();
		repository.persist(userStory);
	}
}

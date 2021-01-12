package com.voluptor.userStories.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import com.voluptor.component.api.Pagination;
import com.voluptor.userStories.dto.userStory.ListUserStoryVersionResponse;
import com.voluptor.userStories.mapper.UserStoryVersionMapper;
import com.voluptor.userStories.model.UserStory;
import com.voluptor.userStories.model.UserStoryVersion;
import com.voluptor.userStories.repository.UserStoryVersionRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

@ApplicationScoped
public class UserStoryVersionService {
	@Inject
	UserStoryVersionRepository repository;
	
	@Inject
	UserStoryVersionMapper mapper;
	
	public ListUserStoryVersionResponse list(Long projectId, Long userStoryId, Integer offset, Integer limit)
	{
		PanacheQuery<UserStoryVersion> query = repository.find(projectId, userStoryId);
		List<UserStoryVersion> userStories = query.range(offset, offset+limit-1).list();
		return new ListUserStoryVersionResponse(mapper.toListResponses(userStories), new Pagination(offset, limit, query.count()));		
	}
	
	public UserStoryVersion get(Long projectId, Long userStoryId, Long id) throws NotFoundException
	{
		UserStoryVersion userStoryVersion = repository.findByProjectIdUserStoryIdId(projectId, userStoryId, id);
		if (userStoryVersion == null) {
			throw new NotFoundException();
		}
		return userStoryVersion;
	}
	
	public UserStoryVersion generateVersion(UserStory userStory) 
	{
		UserStoryVersion version = mapper.from(userStory);
		version.setUserStory(userStory);
		version.setVersion(userStory.getVersion());
		repository.persist(version);
		
		return version;
	}
}

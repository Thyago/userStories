package com.voluptor.userStories.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import com.voluptor.component.api.Pagination;
import com.voluptor.userStories.dto.sprint.CreateSprintRequest;
import com.voluptor.userStories.dto.sprint.ListSprintFilters;
import com.voluptor.userStories.dto.sprint.ListSprintResponse;
import com.voluptor.userStories.dto.sprint.UpdateSprintRequest;
import com.voluptor.userStories.mapper.SprintMapper;
import com.voluptor.userStories.model.Project;
import com.voluptor.userStories.model.Sprint;
import com.voluptor.userStories.repository.SprintRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

@ApplicationScoped
public class SprintService {

	@Inject
	SprintRepository repository;
	
	@Inject
	SprintMapper mapper;
	
	@Inject
	ProjectService projectService;
	
	public ListSprintResponse list(ListSprintFilters listSprintFilters, Integer offset, Integer limit)
	{
		PanacheQuery<Sprint> query = repository.find(listSprintFilters);
		List<Sprint> sprints = query.range(offset, offset+limit-1).list();
		return new ListSprintResponse(mapper.toListResponses(sprints), new Pagination(offset, limit, query.count()));		
	}
	
	public Sprint get(Long projectId, Long id) throws NotFoundException
	{
		Sprint sprint = repository.findByProjectIdId(projectId, id);
		if (sprint == null) {
			throw new NotFoundException();
		}
		return sprint;
	}
	
	@Transactional
	public Sprint create(Long projectId, CreateSprintRequest createSprintRequest)
	{
		Project project = projectService.get(projectId);
						
		Sprint sprint =  mapper.from(createSprintRequest);		
		sprint.setProject(project);

		repository.persist(sprint);

		return sprint;
	}
	
	@Transactional
	public void update(Sprint sprint, UpdateSprintRequest updateSprintRequest)
	{
		mapper.updateFrom(updateSprintRequest, sprint);
		repository.persist(sprint);
	}
	
	@Transactional
	public void delete(Sprint sprint)
	{
		repository.delete(sprint);
	}
}

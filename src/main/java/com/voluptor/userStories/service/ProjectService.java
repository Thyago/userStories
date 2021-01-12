package com.voluptor.userStories.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import com.voluptor.component.api.Pagination;
import com.voluptor.userStories.dto.project.CreateProjectRequest;
import com.voluptor.userStories.dto.project.ListProjectFilters;
import com.voluptor.userStories.dto.project.ListProjectResponse;
import com.voluptor.userStories.dto.project.UpdateProjectRequest;
import com.voluptor.userStories.mapper.ProjectMapper;
import com.voluptor.userStories.model.Project;
import com.voluptor.userStories.repository.ProjectRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

@ApplicationScoped
public class ProjectService {
	@Inject
	ProjectRepository repository;
	
	@Inject
	ProjectMapper mapper;
	
	public ListProjectResponse list(ListProjectFilters listProjectFilters, Integer offset, Integer limit)
	{
		PanacheQuery<Project> query = repository.find(listProjectFilters);
		List<Project> projects = query.range(offset, offset+limit-1).list();
		return new ListProjectResponse(mapper.toListResponses(projects), new Pagination(offset, limit, query.count()));		
	}
	
	
	public Project get(Long id) throws NotFoundException
	{
		Project project = repository.findById(id);
		if (project == null) {
			throw new NotFoundException();
		}
		return project;
	}
	
	@Transactional
	public Project create(CreateProjectRequest createProjectRequest)
	{
		Project project = mapper.from(createProjectRequest);
		
		//TODO: Create current customerProject with owner

		repository.persist(project);

		return project;
	}
	
	@Transactional
	public void update(Project project, UpdateProjectRequest updateProjectRequest)
	{
		mapper.updateFrom(updateProjectRequest, project);
		repository.persist(project);
	}
	
	@Transactional
	public void delete(Project project)
	{
		repository.delete(project);
	}
}

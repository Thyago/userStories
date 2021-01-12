package com.voluptor.userStories.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import com.voluptor.component.api.Pagination;
import com.voluptor.userStories.dto.customerProject.ListCustomerProjectFilters;
import com.voluptor.userStories.dto.customerProject.ListCustomerProjectResponse;
import com.voluptor.userStories.exception.AlreadyExistsException;
import com.voluptor.userStories.exception.CannotRemoveOwnerException;
import com.voluptor.userStories.mapper.CustomerProjectMapper;
import com.voluptor.userStories.model.Customer;
import com.voluptor.userStories.model.CustomerProject;
import com.voluptor.userStories.model.Project;
import com.voluptor.userStories.repository.CustomerProjectRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

@ApplicationScoped
public class CustomerProjectService {

	@Inject
	CustomerProjectRepository repository;
	
	@Inject
	CustomerProjectMapper mapper;
	
	@Inject
	ProjectService projectService;
	
	@Inject
	CustomerService customerService;
	
	public ListCustomerProjectResponse list(ListCustomerProjectFilters listFilters, Integer offset, Integer limit)
	{
		PanacheQuery<CustomerProject> query = repository.find(listFilters);
		List<CustomerProject> customerProjects = query.range(offset, offset+limit-1).list();
		return new ListCustomerProjectResponse(mapper.toListResponses(customerProjects), new Pagination(offset, limit, query.count()));		
	}
	
	public CustomerProject get(Long customerId, Long projectId)
	{
		return this.get(customerId, projectId, null);
	}
	
	public CustomerProject get(Long customerId, Long projectId, Boolean isRemoved) throws NotFoundException
	{
		CustomerProject customerProject = repository.findByCustomerIdProjectId(customerId, projectId, isRemoved);
		if (customerProject == null) {
			throw new NotFoundException();
		}
		return customerProject;
	}
	
	@Transactional
	public CustomerProject create(Long projectId, Long customerId) throws AlreadyExistsException
	{
		//TODO: Check if logged user is the owner of current project		
		
		CustomerProject customerProject = repository.findByCustomerIdProjectId(customerId, projectId);
		if (customerProject != null) {
			throw new AlreadyExistsException();
		}
		
		Project project = projectService.get(projectId);
		Customer customer = customerService.get(customerId);
		
		customerProject = new CustomerProject();
		customerProject.setCustomer(customer);
		customerProject.setProject(project);		
		
		repository.persist(customerProject);

		return customerProject;
	}
	
	@Transactional
	public void remove(CustomerProject customerProject) throws CannotRemoveOwnerException
	{
		if (customerProject.isOwner()) {
			throw new CannotRemoveOwnerException();
		}
		
		customerProject.setRemovedAt(LocalDateTime.now());
		repository.persist(customerProject);
	}
	
	@Transactional
	public void delete(CustomerProject customerProject)
	{
		repository.delete(customerProject);
	}
}

package com.voluptor.userStories.resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.voluptor.userStories.dto.customerProject.GetCustomerProjectResponse;
import com.voluptor.userStories.dto.customerProject.ListCustomerProjectFilters;
import com.voluptor.userStories.dto.customerProject.ListCustomerProjectResponse;
import com.voluptor.userStories.exception.AlreadyExistsException;
import com.voluptor.userStories.exception.CannotRemoveOwnerException;
import com.voluptor.userStories.mapper.CustomerProjectMapper;
import com.voluptor.userStories.model.CustomerProject;
import com.voluptor.userStories.service.CustomerProjectService;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/projects/{projectId}/customers")
@Tag(name = "Project Customers")
public class ProjectCustomerResource {
	
	@Inject
	CustomerProjectService service;
	
	@Inject
	CustomerProjectMapper mapper;	
	
	@GET	
	@Operation(summary = "List customer projects by project")
	@APIResponse(responseCode = "200")
	//@RolesAllowed({"user"})
	public Response listByProject(
			@PathParam("projectId") Long projectId,
			@QueryParam("filter[isRemoved]") Boolean isRemoved,
			@QueryParam("filter[isOwner]") Boolean isOwner,
			@DefaultValue("0") @QueryParam("offset") Integer offset,
			@DefaultValue("50") @QueryParam("limit") Integer limit) {
		
		ListCustomerProjectFilters listFilters = ListCustomerProjectFilters.fromProjectId(projectId);
		listFilters.setIsRemoved(isRemoved);
		listFilters.setIsOwner(isOwner);
		
		ListCustomerProjectResponse listResponse = service.list(listFilters, offset, limit);
		
		return Response.ok(listResponse).build();
	}
	
	@GET
	@Operation(summary = "Returns the customer project for given identifiers")
	@APIResponse(responseCode = "200")
	@APIResponse(responseCode = "404", description = "Not found")
	@Path("{customerId}")
	//@RolesAllowed({"user"})
	public Response show(@PathParam("projectId") Long projectId, @PathParam("customerId") Long customerId) {
		try {
			CustomerProject customerProject = service.get(customerId, projectId);
			
			GetCustomerProjectResponse getResponse = mapper.toGetResponse(customerProject);
			return Response.ok(getResponse).build();
		} catch (NotFoundException ex) {
			return Response.status(404).build();
		}
	}
	
	@POST	
	@Transactional
	@Operation(summary = "Create new project customer")
	@APIResponse(responseCode = "200", description = "Created")
	@APIResponse(responseCode = "403", description = "Already exists")
	@Path("{customerId}/assign")
	public Response create(@PathParam("projectId") Long projectId, @PathParam("customerId") Long customerId, @Context UriInfo uriInfo) {
		try {
			CustomerProject customerProject = service.create(customerId, projectId);
			
			GetCustomerProjectResponse getResponse = mapper.toGetResponse(customerProject);
			return Response.ok(getResponse).build();
		} catch (AlreadyExistsException e) {
			return Response.status(403).build();
		}
	}
	
	@PATCH
	@Transactional
	@Operation(summary = "Removes an existing customer project")
	@APIResponse(responseCode = "200")
	@APIResponse(responseCode = "404", description = "Not found")
	@APIResponse(responseCode = "403", description = "Cannot remove ownership")
	@Path("{customerId}/unassign")
	//@RolesAllowed({"user"})
	public Response remove(@PathParam("projectId") Long projectId, @PathParam("customerId") Long customerId)
	{
		try {
			CustomerProject customerProject = service.get(customerId, projectId);
			service.remove(customerProject);
			
			GetCustomerProjectResponse getResponse = mapper.toGetResponse(customerProject);
			return Response.ok(getResponse).build();
		} catch (NotFoundException ex) {
			return Response.status(404).build();
		} catch (CannotRemoveOwnerException e) {
			return Response.status(403).build();
		}		
	}
	
	@DELETE
	@Transactional
	@Operation(summary = "Deletes an existing customer project")
	@APIResponse(responseCode = "204", description = "Deleted")
	@APIResponse(responseCode = "404", description = "Not found")
	@Path("{customerId}")
	public Response delete(@PathParam("projectId") Long projectId, @PathParam("customerId") Long customerId) {
		try {
			CustomerProject customerProject = service.get(customerId, projectId);
			service.delete(customerProject);
			
			GetCustomerProjectResponse getResponse = mapper.toGetResponse(customerProject);
			return Response.ok(getResponse).build();
		} catch (NotFoundException ex) {
			return Response.status(404).build();
		}	
	}
}

package com.voluptor.userStories.resource;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
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
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.voluptor.userStories.dto.project.CreateProjectRequest;
import com.voluptor.userStories.dto.project.GetProjectResponse;
import com.voluptor.userStories.dto.project.ListProjectFilters;
import com.voluptor.userStories.dto.project.ListProjectResponse;
import com.voluptor.userStories.dto.project.UpdateProjectRequest;
import com.voluptor.userStories.mapper.ProjectMapper;
import com.voluptor.userStories.model.Project;
import com.voluptor.userStories.service.ProjectService;

import io.quarkus.security.Authenticated;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/projects")
@Tag(name = "Projects")
@Authenticated
public class ProjectResource {
	
	@Inject
	ProjectService service;
	
	@Inject
	ProjectMapper mapper;	
	
	@GET	
	@Operation(summary = "List projects")
	@APIResponse(responseCode = "200")
	@RolesAllowed({"user"})
	public Response list(
			@QueryParam("filter[text]") String textSearch,
			@DefaultValue("0") @QueryParam("offset") Integer offset,
			@DefaultValue("50") @QueryParam("limit") Integer limit) {
		
		ListProjectFilters listFilters = new ListProjectFilters();
		listFilters.setSearch(textSearch);
		
		ListProjectResponse listResponse = service.list(listFilters, offset, limit);
		
		return Response.ok(listResponse).build();
	}
	
	
	@GET
	@Operation(summary = "Returns the project for a given identifier")
	@APIResponse(responseCode = "200")
	@APIResponse(responseCode = "404", description = "Not found")
	@Path("/{id}")
	@RolesAllowed({"user"})
	public Response show(@PathParam("id") Long id) {
		try {
			Project project = service.get(id);
			
			GetProjectResponse getProjectResponse = mapper.toGetResponse(project);
			return Response.ok(getProjectResponse).build();
		} catch (NotFoundException ex) {
			return Response.status(404).build();
		}
	}
	
	@POST	
	@Transactional
	@Operation(summary = "Create new project")
	@APIResponse(responseCode = "200", description = "Created")
	@RolesAllowed({"user"})
	public Response create(@RequestBody @Valid CreateProjectRequest createProjectRequest, @Context UriInfo uriInfo) {
		Project project = service.create(createProjectRequest);

		GetProjectResponse getProjectResponse = mapper.toGetResponse(project);
		return Response.ok(getProjectResponse).build();
	}
	
	@PATCH
	@Transactional
	@Operation(summary = "Updates an existing project")
	@APIResponse(responseCode = "200")
	@APIResponse(responseCode = "404", description = "Not found")
	@Path("/{id}")
	@RolesAllowed({"user"})
	public Response update(@PathParam("id") Long id, @Valid UpdateProjectRequest updateProjectRequest)
	{
		try {
			Project project = service.get(id);
			service.update(project, updateProjectRequest);
			
			GetProjectResponse getProjectResponse = mapper.toGetResponse(project);
			return Response.ok(getProjectResponse).build();
		} catch (NotFoundException ex) {
			return Response.status(404).build();
		}		
	}
	
	@DELETE
	@Transactional
	@Operation(summary = "Deletes an existing project")
	@APIResponse(responseCode = "204", description = "Deleted")
	@APIResponse(responseCode = "404", description = "Not found")
	@Path("/{id}")
	@RolesAllowed({"user"})
	public Response delete(@PathParam("id") Long id) {
		try {
			Project project = service.get(id);
			service.delete(project);
			
			return Response.noContent().build();
		} catch (NotFoundException ex) {
			return Response.status(404).build();
		}
	}
}

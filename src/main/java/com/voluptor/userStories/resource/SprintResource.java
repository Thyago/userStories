package com.voluptor.userStories.resource;

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

import com.voluptor.userStories.dto.sprint.CreateSprintRequest;
import com.voluptor.userStories.dto.sprint.GetSprintResponse;
import com.voluptor.userStories.dto.sprint.ListSprintFilters;
import com.voluptor.userStories.dto.sprint.ListSprintResponse;
import com.voluptor.userStories.dto.sprint.UpdateSprintRequest;
import com.voluptor.userStories.mapper.SprintMapper;
import com.voluptor.userStories.model.Sprint;

import com.voluptor.userStories.service.SprintService;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/projects/{projectId}/sprints")
@Tag(name = "Sprints")
public class SprintResource {
	
	@Inject
	SprintService service;
	
	@Inject
	SprintMapper mapper;	
	
	@GET	
	@Operation(summary = "List sprints")
	@APIResponse(responseCode = "200")
	//@RolesAllowed({"user"})
	public Response list(
			@PathParam("projectId") Long projectId,
			@QueryParam("filter[text]") String textSearch,
			@DefaultValue("0") @QueryParam("offset") Integer offset,
			@DefaultValue("50") @QueryParam("limit") Integer limit) {
		
		ListSprintFilters listFilters = new ListSprintFilters(projectId);
		listFilters.setSearch(textSearch);
		
		ListSprintResponse listResponse = service.list(listFilters, offset, limit);
		
		return Response.ok(listResponse).build();
	}
	
	@GET
	@Operation(summary = "Returns the sprint for a given identifier")
	@APIResponse(responseCode = "200")
	@APIResponse(responseCode = "404", description = "Not found")
	@Path("/{id}")
	//@RolesAllowed({"user"})
	public Response show(@PathParam("projectId") Long projectId, @PathParam("id") Long id) {
		try {
			Sprint sprint = service.get(projectId, id);
			
			GetSprintResponse getSprintResponse = mapper.toGetResponse(sprint);
			return Response.ok(getSprintResponse).build();
		} catch (NotFoundException ex) {
			return Response.status(404).build();
		}
	}
	
	@POST	
	@Transactional
	@Operation(summary = "Create new sprint")
	@APIResponse(responseCode = "200", description = "Created")
	public Response create(@PathParam("projectId") Long projectId, @RequestBody @Valid CreateSprintRequest createSprintRequest, @Context UriInfo uriInfo) {
		Sprint sprint = service.create(projectId, createSprintRequest);

		GetSprintResponse getSprintResponse = mapper.toGetResponse(sprint);
		return Response.ok(getSprintResponse).build();
	}
	
	@PATCH
	@Transactional
	@Operation(summary = "Updates an existing sprint")
	@APIResponse(responseCode = "200")
	@APIResponse(responseCode = "404", description = "Not found")
	@Path("/{id}")
	//@RolesAllowed({"user"})
	public Response update(@PathParam("projectId") Long projectId, @PathParam("id") Long id, @RequestBody UpdateSprintRequest updateSprintRequest)
	{
		try {
			Sprint sprint = service.get(projectId, id);
			service.update(sprint, updateSprintRequest);
			
			GetSprintResponse getSprintResponse = mapper.toGetResponse(sprint);
			return Response.ok(getSprintResponse).build();
		} catch (NotFoundException ex) {
			return Response.status(404).build();
		}		
	}
	
	@DELETE
	@Transactional
	@Operation(summary = "Deletes an existing sprint")
	@APIResponse(responseCode = "204", description = "Deleted")
	@APIResponse(responseCode = "404", description = "Not found")
	@Path("/{id}")
	public Response delete(@PathParam("projectId") Long projectId, @PathParam("id") Long id) {
		try {
			Sprint sprint = service.get(projectId, id);
			service.delete(sprint);
			
			return Response.noContent().build();
		} catch (NotFoundException ex) {
			return Response.status(404).build();
		}
	}
}

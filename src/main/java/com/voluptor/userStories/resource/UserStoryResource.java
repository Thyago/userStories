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

import com.voluptor.component.archivable.exception.ArchivedException;
import com.voluptor.component.archivable.exception.UnarchivedException;
import com.voluptor.userStories.dto.userStory.CreateUserStoryRequest;
import com.voluptor.userStories.dto.userStory.GetUserStoryResponse;
import com.voluptor.userStories.dto.userStory.ListUserStoryFilters;
import com.voluptor.userStories.dto.userStory.ListUserStoryResponse;
import com.voluptor.userStories.dto.userStory.UpdateUserStoryRequest;
import com.voluptor.userStories.mapper.UserStoryMapper;
import com.voluptor.userStories.model.UserStory;
import com.voluptor.userStories.service.UserStoryService;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/projects/{projectId}/userStories")
@Tag(name = "User Stories")
public class UserStoryResource {

	@Inject
	UserStoryService service;
	
	@Inject
	UserStoryMapper mapper;	
	
	@GET	
	@Operation(summary = "List user stories")
	@APIResponse(responseCode = "200")
	//@RolesAllowed({"user"})
	public Response list(
			@PathParam("projectId") Long projectId,
			@QueryParam("filter[sprintId]") Long sprintId,
			@QueryParam("filter[userTypeId]") Long userTypeId,
			@QueryParam("filter[businessValue][min]") Integer businessValueMin,
			@QueryParam("filter[businessValue][max]") Integer businessValueMax,
			@QueryParam("filter[size][min]") Integer sizeMin,
			@QueryParam("filter[size][max]") Integer sizeMax,
			@QueryParam("filter[text]") String textSearch,
			@DefaultValue("0") @QueryParam("offset") Integer offset,
			@DefaultValue("50") @QueryParam("limit") Integer limit) {
		
		ListUserStoryFilters listFilters = new ListUserStoryFilters(projectId);
		listFilters.setSprintId(sprintId);
		listFilters.setUserTypeId(userTypeId);
		listFilters.setBusinessValueMin(businessValueMin);
		listFilters.setBusinessValueMin(businessValueMin);
		listFilters.setSizeMin(sizeMin);
		listFilters.setSizeMax(sizeMax);
		listFilters.setSearch(textSearch);
		
		ListUserStoryResponse listResponse = service.list(listFilters, offset, limit);
		
		return Response.ok(listResponse).build();
	}
	
	@GET
	@Operation(summary = "Returns the user story for a given identifier")
	@APIResponse(responseCode = "200")
	@APIResponse(responseCode = "404", description = "Not found")
	@Path("/{id}")
	//@RolesAllowed({"user"})
	public Response show(@PathParam("projectId") Long projectId, @PathParam("id") Long id) {
		try {
			UserStory userStory = service.get(projectId, id);
			
			GetUserStoryResponse getUserStoryResponse = mapper.toGetResponse(userStory);
			return Response.ok(getUserStoryResponse).build();
		} catch (NotFoundException ex) {
			return Response.status(404).build();
		}
	}
	
	@POST	
	@Transactional
	@Operation(summary = "Create new user story")
	@APIResponse(responseCode = "200", description = "Created")
	public Response create(@PathParam("projectId") Long projectId, @RequestBody @Valid CreateUserStoryRequest createUserStoryRequest, @Context UriInfo uriInfo) 
	{
		UserStory userStory = service.create(projectId, createUserStoryRequest);

		GetUserStoryResponse getUserStoryResponse = mapper.toGetResponse(userStory);
		return Response.ok(getUserStoryResponse).build();
	}
	
	@PATCH
	@Transactional
	@Operation(summary = "Updates an existing user story")
	@APIResponse(responseCode = "200")
	@APIResponse(responseCode = "404", description = "Not found")
	@Path("/{id}")
	//@RolesAllowed({"user"})
	public Response update(@PathParam("projectId") Long projectId, @PathParam("id") Long id, @RequestBody UpdateUserStoryRequest updateUserStoryRequest)
	{
		try {
			UserStory userStory = service.get(projectId, id);
			service.update(userStory, updateUserStoryRequest);
			
			GetUserStoryResponse getUserStoryResponse = mapper.toGetResponse(userStory);
			return Response.ok(getUserStoryResponse).build();
		} catch (NotFoundException ex) {
			return Response.status(404).build();
		}		
	}
	
	@POST
	@Transactional
	@Operation(summary = "Archives an existing user story")
	@APIResponse(responseCode = "204")
	@APIResponse(responseCode = "404", description = "Not found")
	@APIResponse(responseCode = "409", description = "Cannot archive an archived user story")
	@Path("/{id}/archive")
	public Response archive(@PathParam("projectId") Long projectId, @PathParam("id") Long id) {
		try {
			UserStory userStory = service.get(projectId, id);
			service.archive(userStory);

			return Response.noContent().build();
		} catch (NotFoundException ex) {
			return Response.status(404).build();
		} catch (ArchivedException ex) {
			return Response.status(409).build();
		}		
	}
	
	@POST
	@Transactional
	@Operation(summary = "Unarchives an existing archived user story")
	@APIResponse(responseCode = "204")
	@APIResponse(responseCode = "404", description = "Not found")
	@APIResponse(responseCode = "409", description = "Cannot unarchive a unarchived user story")
	@Path("/{id}/unarchive")
	public Response unarchive(@PathParam("projectId") Long projectId, @PathParam("id") Long id) {
		try {
			UserStory userStory = service.get(projectId, id);
			service.unarchive(userStory);

			return Response.noContent().build();
		} catch (NotFoundException ex) {
			return Response.status(404).build();
		} catch (UnarchivedException ex) {
			return Response.status(409).build();
		}
	}
	
	@DELETE
	@Transactional
	@Operation(summary = "Deletes an existing user story")
	@APIResponse(responseCode = "204", description = "Deleted")
	@APIResponse(responseCode = "404", description = "Not found")
	@Path("/{id}")
	public Response delete(@PathParam("projectId") Long projectId, @PathParam("id") Long id) {
		try {
			UserStory userStory = service.get(projectId, id);
			service.delete(userStory);
			
			return Response.noContent().build();
		} catch (NotFoundException ex) {
			return Response.status(404).build();
		}
	}
}

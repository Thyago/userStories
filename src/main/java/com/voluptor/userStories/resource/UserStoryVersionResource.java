package com.voluptor.userStories.resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.voluptor.userStories.dto.userStory.GetUserStoryResponse;
import com.voluptor.userStories.dto.userStory.GetUserStoryVersionResponse;
import com.voluptor.userStories.dto.userStory.ListUserStoryVersionResponse;
import com.voluptor.userStories.mapper.UserStoryMapper;
import com.voluptor.userStories.mapper.UserStoryVersionMapper;
import com.voluptor.userStories.model.UserStory;
import com.voluptor.userStories.model.UserStoryVersion;
import com.voluptor.userStories.service.UserStoryService;
import com.voluptor.userStories.service.UserStoryVersionService;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/projects/{projectId}/userStories/{userStoryId}/versions")
@Tag(name = "User Story Versions")
public class UserStoryVersionResource {
	
	@Inject
	UserStoryVersionService service;
	
	@Inject
	UserStoryVersionMapper mapper;	
	
	@Inject
	UserStoryService userStoryService;
	
	@Inject
	UserStoryMapper userStoryMapper;
	
	@GET	
	@Operation(summary = "List user story versions")
	@APIResponse(responseCode = "200")
	//@RolesAllowed({"user"})
	public Response list(
			@PathParam("projectId") Long projectId,
			@PathParam("userStoryId") Long userStoryId,
			@DefaultValue("0") @QueryParam("offset") Integer offset,
			@DefaultValue("50") @QueryParam("limit") Integer limit) {
		
		ListUserStoryVersionResponse listResponse = service.list(projectId, userStoryId, offset, limit);
		
		return Response.ok(listResponse).build();
	}
	
	@GET
	@Operation(summary = "Returns the user story version for a given identifier")
	@APIResponse(responseCode = "200")
	@APIResponse(responseCode = "404", description = "Not found")
	@Path("/{id}")
	//@RolesAllowed({"user"})
	public Response show(@PathParam("projectId") Long projectId, @PathParam("userStoryId") Long userStoryId, @PathParam("id") Long id) {
		try {
			UserStoryVersion userStoryVersion = service.get(projectId, userStoryId, id);
			
			GetUserStoryVersionResponse getUserStoryVersionResponse = mapper.toGetResponse(userStoryVersion);
			return Response.ok(getUserStoryVersionResponse).build();
		} catch (NotFoundException ex) {
			return Response.status(404).build();
		}
	}
	
	@POST
	@Operation(summary = "Restores the content of the specified user story version")
	@APIResponse(responseCode = "200")
	@APIResponse(responseCode = "404", description = "Not found")
	@Path("/{id}/restore")
	//@RolesAllowed({"user"})
	public Response restore(@PathParam("projectId") Long projectId, @PathParam("userStoryId") Long userStoryId, @PathParam("id") Long id) {
		
		try {
			UserStoryVersion userStoryVersion = service.get(projectId, userStoryId, id);
			UserStory userStory = userStoryService.restore(userStoryVersion);
			
			GetUserStoryResponse getUserStoryResponse = userStoryMapper.toGetResponse(userStory);
			return Response.ok(getUserStoryResponse).build();
		} catch (NotFoundException ex) {
			return Response.status(404).build();
		}
	}
}

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
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.voluptor.userStories.dto.userType.CreateUserTypeRequest;
import com.voluptor.userStories.dto.userType.GetUserTypeResponse;
import com.voluptor.userStories.dto.userType.ListUserTypeFilters;
import com.voluptor.userStories.dto.userType.ListUserTypeResponse;
import com.voluptor.userStories.dto.userType.UpdateUserTypeRequest;
import com.voluptor.userStories.mapper.UserTypeMapper;
import com.voluptor.userStories.model.UserType;
import com.voluptor.userStories.service.UserTypeService;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/projects/{projectId}/userTypes")
@Tag(name = "User Types")
public class UserTypeResource {

	@Inject
	UserTypeService service;
	
	@Inject
	UserTypeMapper mapper;	
	
	@GET	
	@Operation(summary = "List user types")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ListUserTypeResponse.class, type = SchemaType.ARRAY)))
	//@RolesAllowed({"user"})
	public Response list(
			@PathParam("projectId") Long projectId,
			@QueryParam("filter[text]") String textSearch,
			@DefaultValue("0") @QueryParam("offset") Integer offset,
			@DefaultValue("50") @QueryParam("limit") Integer limit) {
		
		ListUserTypeFilters listFilters = new ListUserTypeFilters(projectId);
		listFilters.setSearch(textSearch);
		
		ListUserTypeResponse listResponse = service.list(listFilters, offset, limit);
		
		return Response.ok(listResponse).build();
	}
	
	@GET
	@Operation(summary = "Returns the user type for a given identifier")
	@APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetUserTypeResponse.class, type = SchemaType.OBJECT)))
	@APIResponse(responseCode = "404", description = "Not found")
	@Path("/{id}")
	//@RolesAllowed({"user"})
	public Response show(@PathParam("projectId") Long projectId, @PathParam("id") Long id) {
		try {
			UserType userType = service.get(projectId, id);
			
			GetUserTypeResponse getUserTypeResponse = mapper.toGetResponse(userType);
			return Response.ok(getUserTypeResponse).build();
		} catch (NotFoundException ex) {
			return Response.status(404).build();
		}
	}
	
	@POST	
	@Transactional
	@Operation(summary = "Create new user type")
	@APIResponse(responseCode = "200", description = "Created")
	public Response create(@PathParam("projectId") Long projectId, @RequestBody @Valid CreateUserTypeRequest createUserTypeRequest, @Context UriInfo uriInfo) 
	{
		UserType userType = service.create(projectId, createUserTypeRequest);

		GetUserTypeResponse getUserTypeResponse = mapper.toGetResponse(userType);
		return Response.ok(getUserTypeResponse).build();
	}
	
	@PATCH
	@Transactional
	@Operation(summary = "Updates an existing user type")
	@APIResponse(responseCode = "200")
	@APIResponse(responseCode = "404", description = "Not found")
	@Path("/{id}")
	//@RolesAllowed({"user"})
	public Response update(@PathParam("projectId") Long projectId, @PathParam("id") Long id, @RequestBody UpdateUserTypeRequest updateUserTypeRequest)
	{
		try {
			UserType userType = service.get(projectId, id);
			service.update(userType, updateUserTypeRequest);
			
			GetUserTypeResponse getUserTypeResponse = mapper.toGetResponse(userType);
			return Response.ok(getUserTypeResponse).build();
		} catch (NotFoundException ex) {
			return Response.status(404).build();
		}		
	}
	
	@DELETE
	@Transactional
	@Operation(summary = "Deletes an existing user type")
	@APIResponse(responseCode = "204", description = "Deleted")
	@APIResponse(responseCode = "404", description = "Not found")
	@Path("/{id}")
	public Response delete(@PathParam("projectId") Long projectId, @PathParam("id") Long id) {
		try {
			UserType userType = service.get(projectId, id);
			service.delete(userType);
			
			return Response.noContent().build();
		} catch (NotFoundException ex) {
			return Response.status(404).build();
		}
	}
}

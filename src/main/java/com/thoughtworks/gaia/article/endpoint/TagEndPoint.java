package com.thoughtworks.gaia.article.endpoint;

import com.thoughtworks.gaia.article.entity.Tag;
import com.thoughtworks.gaia.article.service.TagService;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Component
@Path("tags")
@Api(value = "tags", description = "Access to tag resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagEndPoint {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private TagService tagService;

    @Path("/{tag_id}")
    @ApiOperation(value = "Get tag by id", response = Tag.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get tag successfully"),
            @ApiResponse(code = 404, message = "No tag matches given id")
    })
    @GET
    public Response getTag(@PathParam("tag_id") Long tagId) {
        Tag tag = tagService.get(tagId);
        if (tag == null) {
            throw new NotFoundException();
        }
        return Response.ok().entity(tag).build();
    }
}

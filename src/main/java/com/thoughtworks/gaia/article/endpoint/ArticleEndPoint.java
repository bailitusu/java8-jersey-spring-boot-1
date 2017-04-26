package com.thoughtworks.gaia.article.endpoint;

import com.thoughtworks.gaia.article.entity.Article;
import com.thoughtworks.gaia.article.service.ArticleService;
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
@Path("articles")
@Api(value = "articles", description = "Access to article resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArticleEndPoint {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private ArticleService articleService;

    @Path("/{article_id}")
    @ApiOperation(value = "Get article by id", response = Article.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get article successfully"),
            @ApiResponse(code = 404, message = "No article matches given id")
    })
    @GET
    public Response getArticle(@PathParam("article_id") Long articleId) {
        Article article = articleService.get(articleId);
        if (article == null) {
            throw new NotFoundException();
        }
        return Response.ok().entity(article).build();
    }

    @ApiOperation(value = "create article", response = Article.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "create article successfully")
    })
    @POST
    public Response createArticle(Article article) {
        Article articleCreated = articleService.create(article);
        return Response.created(uriInfo.getRequestUriBuilder().build("/" + articleCreated.getId()))
                .entity(articleCreated).build();
    }
}

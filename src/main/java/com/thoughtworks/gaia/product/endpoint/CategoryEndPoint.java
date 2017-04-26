package com.thoughtworks.gaia.product.endpoint;

import com.thoughtworks.gaia.product.entity.Category;
import com.thoughtworks.gaia.product.entity.Product;
import com.thoughtworks.gaia.product.model.ProductModel;
import com.thoughtworks.gaia.product.service.CategoryService;
import com.thoughtworks.gaia.product.service.ProductService;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Path("category")
@Api(value = "category", description = "Access to category resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryEndPoint {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private CategoryService categoryService;

    @Path("/{category_id}")
    @ApiOperation(value = "Get product by id", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get product successfully"),
            @ApiResponse(code = 404, message = "No product matches given id")
    })
    @GET
    public Response getCategory(@PathParam("category_id") Long categoryId) {
        Category category = categoryService.getOne(categoryId);
        return Response.ok().entity(category).build();
    }

    @POST
    public Response getCategory(Category category) {
        Category createdCategory = categoryService.save(category);
        return Response.created(uriInfo.getRequestUriBuilder().build("/" + createdCategory.getId())).entity(createdCategory).build();
    }

    @Path("/{category_id}/products/{product_id}")
    @GET
    public Response getProduct(@PathParam("category_id") Long categoryId, @PathParam("product_id") Long productId) {
        Category category = categoryService.getOne(categoryId);
        List<Product> products = category.getProducts();
        Optional<Product> filteredProduct = products.stream().filter(product -> product.getId().equals(productId)).findFirst();
        if (filteredProduct.isPresent()) {
            return Response.ok().entity(filteredProduct.get()).build();
        }
        throw new NotFoundException();
    }
}

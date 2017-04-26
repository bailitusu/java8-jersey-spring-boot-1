package com.thoughtworks.gaia.product.endpoint;

import com.thoughtworks.gaia.product.entity.Product;
import com.thoughtworks.gaia.product.model.ProductModel;
import com.thoughtworks.gaia.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Component
@Path("product")
@Api(value = "product", description = "Access to product resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductEndPoint {
    @Autowired
    private ProductService productService;

    @Path("/{product_id}")
    @ApiOperation(value = "Get product by id", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get product successfully"),
            @ApiResponse(code = 404, message = "No product matches given id")
    })
    @GET
    public Response getProduct(@PathParam("product_id") Long productId) {
        Product product = productService.getProduct(productId);
        return Response.ok().entity(product).build();
    }

    @POST
    public Response saveProduct(Product product) throws URISyntaxException {
        Product newProduct = productService.create(product);
        return Response.created(new URI("/gaia/rest/product/" + newProduct.getId())).entity(newProduct).build();
    }

    @PUT
    @Path("/{product_id}")
    public Response updateProduct(@PathParam("product_id") Long productId, Product product) throws URISyntaxException {
        product.setId(productId);
        Product updatedProd = productService.update(product);
        return Response.ok(updatedProd).location(new URI("/gaia/rest/product/" + updatedProd.getId())).build();
    }
}

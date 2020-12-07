package com.zelinskiyrk.store.product.controller;

import com.zelinskiyrk.store.base.api.response.OkResponse;
import com.zelinskiyrk.store.base.api.response.SearchResponse;
import com.zelinskiyrk.store.product.api.request.ProductRequest;
import com.zelinskiyrk.store.product.api.request.ProductSearchRequest;
import com.zelinskiyrk.store.product.api.response.ProductResponse;
import com.zelinskiyrk.store.product.exception.ProductExistException;
import com.zelinskiyrk.store.product.exception.ProductNotExistException;
import com.zelinskiyrk.store.product.mapping.ProductMapping;
import com.zelinskiyrk.store.product.routes.ProductApiRoutes;
import com.zelinskiyrk.store.product.service.ProductApiService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequiredArgsConstructor
@Api(value = "Product API")
public class ProductApiController {
    private final ProductApiService productApiService;

    @PostMapping(ProductApiRoutes.ADMIN)
    @ApiOperation(value = "Add new product", notes = "Use this if you need to add a new product. You need administrator rights to add a product.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Product already exist")
    })
    public OkResponse<ProductResponse> addProduct(@RequestBody ProductRequest request) throws ProductExistException {

        return OkResponse.of(ProductMapping.getInstance().getResponse().convert(
                productApiService.addProduct(request)));
    }

    @GetMapping(ProductApiRoutes.BY_ID)
    @ApiOperation(value = "Find product by ID", notes = "Use this if you need to find a product.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public OkResponse<ProductResponse> byId(
            @ApiParam(value = "Product ID")
            @PathVariable ObjectId id) throws ChangeSetPersister.NotFoundException {
        return OkResponse.of(ProductMapping.getInstance().getResponse().convert(
                productApiService.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new)
        ));
    }

    @GetMapping(ProductApiRoutes.ROOT)
    @ApiOperation(value = "Search products", notes = "Use this if you want to list all products or search product by productName.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    public OkResponse<SearchResponse<ProductResponse>> search(
            @ModelAttribute ProductSearchRequest request
            ){
        return OkResponse.of(ProductMapping.getInstance().getSearch().convert(
                productApiService.search(request)
        ));
    }

    @PutMapping(ProductApiRoutes.ADMIN_BY_ID)
    @ApiOperation(value = "Update product by ID", notes = "Use this if you want to update product. You need administrator rights to update a product.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Product ID invalid")
    })
    public OkResponse<ProductResponse> updateById(
            @ApiParam(value = "Product ID")
            @PathVariable String id,
            @RequestBody ProductRequest productRequest
            ) throws ProductNotExistException {
        return OkResponse.of(ProductMapping.getInstance().getResponse().convert(
                productApiService.update(productRequest)
        ));
    }

    @DeleteMapping(ProductApiRoutes.ADMIN_BY_ID)
    @ApiOperation(value = "Delete product by ID", notes = "Use this if you want to delete product. You need administrator rights to delete a product.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    public OkResponse<String> deleteById(
            @ApiParam(value = "Product ID")
            @PathVariable ObjectId id){
        productApiService.delete(id);
        return OkResponse.of(HttpStatus.OK.toString());
    }
}

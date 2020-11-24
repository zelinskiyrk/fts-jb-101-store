package com.zelinskiyrk.store.category.controller;

import com.zelinskiyrk.store.base.api.response.OkResponse;
import com.zelinskiyrk.store.city.exception.CityNotExistException;
import com.zelinskiyrk.store.category.api.request.CategoryRequest;
import com.zelinskiyrk.store.category.api.response.CategoryResponse;
import com.zelinskiyrk.store.category.exception.CategoryExistException;
import com.zelinskiyrk.store.category.exception.CategoryNotExistException;
import com.zelinskiyrk.store.category.mapping.CategoryMapping;
import com.zelinskiyrk.store.category.routes.CategoryApiRoutes;
import com.zelinskiyrk.store.category.service.CategoryApiService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value = "Category API")
public class CategoryApiController {
    private final CategoryApiService categoryApiService;

    @PostMapping(CategoryApiRoutes.ADMIN)
    @ApiOperation(value = "Add new category", notes = "Use this if you need to add a new category. You need administrator rights to add a category.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Category already exist")
    })
    public OkResponse<CategoryResponse> addCategory(@RequestBody CategoryRequest request) throws CategoryExistException, CityNotExistException {
        return OkResponse.of(CategoryMapping.getInstance().getResponse().convert(
                categoryApiService.addCategory(request)));
    }

    @GetMapping(CategoryApiRoutes.BY_ID)
    @ApiOperation(value = "Find category by ID", notes = "Use this if you need to find a category.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Category not found")
    })
    public OkResponse<CategoryResponse> byId(
            @ApiParam(value = "User ID")
            @PathVariable ObjectId id) throws ChangeSetPersister.NotFoundException {
        return OkResponse.of(CategoryMapping.getInstance().getResponse().convert(
                categoryApiService.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new)
        ));
    }

    @GetMapping(CategoryApiRoutes.ROOT)
    @ApiOperation(value = "Search all categories", notes = "Use this if you want to list all categories.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    public OkResponse<List<CategoryResponse>> search(){
        return OkResponse.of(CategoryMapping.getInstance().getSearch().convert(
                categoryApiService.search()
        ));
    }

    @PutMapping(CategoryApiRoutes.ADMIN_BY_ID)
    @ApiOperation(value = "Update category by ID", notes = "Use this if you want to update category. You need administrator rights to update a category.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Category ID invalid")
    })
    public OkResponse<CategoryResponse> updateById(
            @ApiParam(value = "Category ID")
            @PathVariable String id,
            @RequestBody CategoryRequest categoryRequest
            ) throws CategoryNotExistException {
        return OkResponse.of(CategoryMapping.getInstance().getResponse().convert(
                categoryApiService.update(categoryRequest)
        ));
    }

    @DeleteMapping(CategoryApiRoutes.ADMIN_BY_ID)
    @ApiOperation(value = "Delete category by ID", notes = "Use this if you want to delete category. You need administrator rights to delete a category.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    public OkResponse<String> deleteById(
            @ApiParam(value = "Category ID")
            @PathVariable ObjectId id){
        categoryApiService.delete(id);
        return OkResponse.of(HttpStatus.OK.toString());
    }
}

package com.zelinskiyrk.store.photo.controller;

import com.zelinskiyrk.store.base.api.response.OkResponse;
import com.zelinskiyrk.store.base.api.response.SearchResponse;
import com.zelinskiyrk.store.photo.api.request.PhotoRequest;
import com.zelinskiyrk.store.photo.api.request.PhotoSearchRequest;
import com.zelinskiyrk.store.photo.api.response.PhotoResponse;
import com.zelinskiyrk.store.photo.exception.PhotoNotExistException;
import com.zelinskiyrk.store.photo.mapping.PhotoMapping;
import com.zelinskiyrk.store.photo.routes.PhotoApiRoutes;
import com.zelinskiyrk.store.photo.service.PhotoApiService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Api(value = "Photo API")
public class PhotoApiController {
    private final PhotoApiService photoApiService;

    @GetMapping(PhotoApiRoutes.BY_ID)
    @ApiOperation(value = "Find photo by ID", notes = "Use this if you need to find a photo.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Photo not found")
    })
    public OkResponse<PhotoResponse> byId(
            @ApiParam(value = "Photo ID")
            @PathVariable ObjectId id) throws ChangeSetPersister.NotFoundException {
        return OkResponse.of(PhotoMapping.getInstance().getResponse().convert(
                photoApiService.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new)
        ));
    }

    @GetMapping(PhotoApiRoutes.ROOT)
    @ApiOperation(value = "Search all photos", notes = "Use this if you want to list all photos.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    public OkResponse<SearchResponse<PhotoResponse>> search(
            @ModelAttribute PhotoSearchRequest request
            ){
        return OkResponse.of(PhotoMapping.getInstance().getSearch().convert(
                photoApiService.search(request)
        ));
    }

    @PutMapping(PhotoApiRoutes.ADMIN_BY_ID)
    @ApiOperation(value = "Update photo by ID", notes = "Use this if you want to update photo. You need administrator rights to update a photo.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Photo ID invalid")
    })
    public OkResponse<PhotoResponse> updateById(
            @ApiParam(value = "Photo ID")
            @PathVariable String id,
            @RequestBody PhotoRequest photoRequest
            ) throws PhotoNotExistException {
        return OkResponse.of(PhotoMapping.getInstance().getResponse().convert(
                photoApiService.update(photoRequest)
        ));
    }

    @DeleteMapping(PhotoApiRoutes.ADMIN_BY_ID)
    @ApiOperation(value = "Delete photo by ID", notes = "Use this if you want to delete photo. You need administrator rights to delete a photo.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    public OkResponse<String> deleteById(
            @ApiParam(value = "Photo ID")
            @PathVariable ObjectId id){
        photoApiService.delete(id);
        return OkResponse.of(HttpStatus.OK.toString());
    }
}

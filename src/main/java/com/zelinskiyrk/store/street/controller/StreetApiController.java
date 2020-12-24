package com.zelinskiyrk.store.street.controller;

import com.zelinskiyrk.store.base.api.response.OkResponse;
import com.zelinskiyrk.store.city.exception.CityNotExistException;
import com.zelinskiyrk.store.street.api.request.StreetRequest;
import com.zelinskiyrk.store.street.api.response.StreetResponse;
import com.zelinskiyrk.store.street.exception.StreetExistException;
import com.zelinskiyrk.store.street.exception.StreetNotExistException;
import com.zelinskiyrk.store.street.mapping.StreetMapping;
import com.zelinskiyrk.store.street.routes.StreetApiRoutes;
import com.zelinskiyrk.store.street.service.StreetApiService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Api(value = "Street API")
public class StreetApiController {
    private final StreetApiService streetApiService;

    @PostMapping(StreetApiRoutes.ADMIN)
    @ApiOperation(value = "Add new street", notes = "Use this if you need to add a new street. You need administrator rights to add a street.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Street already exist")
    })
    public OkResponse<StreetResponse> addStreet(@RequestBody StreetRequest request) throws StreetExistException, CityNotExistException {
        return OkResponse.of(StreetMapping.getInstance().getResponse().convert(
                streetApiService.addStreet(request)));
    }

    @GetMapping(StreetApiRoutes.BY_ID)
    @ApiOperation(value = "Find street by ID", notes = "Use this if you need to find a street.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Street not found")
    })
    public OkResponse<StreetResponse> byId(
            @ApiParam(value = "Street ID")
            @PathVariable ObjectId id) throws ChangeSetPersister.NotFoundException {
        return OkResponse.of(StreetMapping.getInstance().getResponse().convert(
                streetApiService.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new)
        ));
    }

    @GetMapping(StreetApiRoutes.ROOT)
    @ApiOperation(value = "Search all streets", notes = "Use this if you want to list all streets.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    public OkResponse<List<String>> search(
            @ModelAttribute StreetRequest request
    ) {
//        return OkResponse.of(StreetMapping.getInstance().getSearch().convert(
//                streetApiService.search(request)
//        ));
        return OkResponse.of(streetApiService.search(request));
    }

    @PutMapping(StreetApiRoutes.ADMIN_BY_ID)
    @ApiOperation(value = "Update street by ID", notes = "Use this if you want to update street. You need administrator rights to update a street.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Street ID invalid")
    })
    public OkResponse<StreetResponse> updateById(
            @ApiParam(value = "Street ID")
            @PathVariable String id,
            @RequestBody StreetRequest streetRequest
    ) throws StreetNotExistException {
        return OkResponse.of(StreetMapping.getInstance().getResponse().convert(
                streetApiService.update(streetRequest)
        ));
    }

    @DeleteMapping(StreetApiRoutes.ADMIN_BY_ID)
    @ApiOperation(value = "Delete street by ID", notes = "Use this if you want to delete street. You need administrator rights to delete a street.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    public OkResponse<String> deleteById(
            @ApiParam(value = "Street ID")
            @PathVariable ObjectId id) {
        streetApiService.delete(id);
        return OkResponse.of(HttpStatus.OK.toString());
    }
}

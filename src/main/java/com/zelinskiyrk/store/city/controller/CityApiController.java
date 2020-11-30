package com.zelinskiyrk.store.city.controller;

import com.zelinskiyrk.store.base.api.response.OkResponse;
import com.zelinskiyrk.store.city.api.request.AddCityRequest;
import com.zelinskiyrk.store.city.api.request.CityRequest;
import com.zelinskiyrk.store.city.api.response.CityResponse;
import com.zelinskiyrk.store.city.exception.CityExistException;
import com.zelinskiyrk.store.city.exception.CityNotExistException;
import com.zelinskiyrk.store.city.mapping.CityMapping;
import com.zelinskiyrk.store.city.routes.CityApiRoutes;
import com.zelinskiyrk.store.city.service.CityApiService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value = "City API")
public class CityApiController {
    private final CityApiService cityApiService;

    @PostMapping(CityApiRoutes.ADMIN)
    @ApiOperation(value = "Add new city", notes = "Use this if you need to add a new city. You need administrator rights to add a city.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "City already exist")
    })
    public OkResponse<CityResponse> addCity(@RequestBody AddCityRequest request) throws CityExistException {
        return OkResponse.of(CityMapping.getInstance().getResponse().convert(
                cityApiService.addCity(request)));
    }

    @GetMapping(CityApiRoutes.BY_ID)
    @ApiOperation(value = "Find city by ID", notes = "Use this if you need to find a city.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "City not found")
    })
    public OkResponse<CityResponse> byId(
            @ApiParam(value = "User ID")
            @PathVariable ObjectId id) throws ChangeSetPersister.NotFoundException {
        return OkResponse.of(CityMapping.getInstance().getResponse().convert(
                cityApiService.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new)
        ));
    }

    @GetMapping(CityApiRoutes.ROOT)
    @ApiOperation(value = "Search all cities", notes = "Use this if you want to list all cities.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    public OkResponse<List<CityResponse>> search(){
        return OkResponse.of(CityMapping.getInstance().getSearch().convert(
                cityApiService.search()
        ));
    }

    @PutMapping(CityApiRoutes.ADMIN_BY_ID)
    @ApiOperation(value = "Update city by ID", notes = "Use this if you want to update city. You need administrator rights to update a city.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "City ID invalid")
    })
    public OkResponse<CityResponse> updateById(
            @ApiParam(value = "City ID")
            @PathVariable String id,
            @RequestBody CityRequest cityRequest
            ) throws CityNotExistException {
        return OkResponse.of(CityMapping.getInstance().getResponse().convert(
                cityApiService.update(cityRequest)
        ));
    }

    @DeleteMapping(CityApiRoutes.ADMIN_BY_ID)
    @ApiOperation(value = "Delete city by ID", notes = "Use this if you want to delete city. You need administrator rights to delete a city.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    public OkResponse<String> deleteById(
            @ApiParam(value = "City ID")
            @PathVariable ObjectId id){
        cityApiService.delete(id);
        return OkResponse.of(HttpStatus.OK.toString());
    }
}

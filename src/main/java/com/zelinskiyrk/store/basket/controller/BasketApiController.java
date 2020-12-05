package com.zelinskiyrk.store.basket.controller;

import com.zelinskiyrk.store.base.api.request.SearchRequest;
import com.zelinskiyrk.store.base.api.response.OkResponse;
import com.zelinskiyrk.store.base.api.response.SearchResponse;
import com.zelinskiyrk.store.basket.api.request.BasketRequest;
import com.zelinskiyrk.store.basket.api.response.BasketResponse;
import com.zelinskiyrk.store.basket.exception.BasketExistException;
import com.zelinskiyrk.store.basket.exception.BasketNotExistException;
import com.zelinskiyrk.store.basket.mapping.BasketMapping;
import com.zelinskiyrk.store.basket.routes.BasketApiRoutes;
import com.zelinskiyrk.store.basket.service.BasketApiService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Api(value = "Basket API")
public class BasketApiController {
    private final BasketApiService basketApiService;

    @PostMapping(BasketApiRoutes.ROOT)
    @ApiOperation(value = "Add new basket", notes = "Use this if you need to add a new basket. You need administrator rights to add a basket.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Basket already exist")
    })
    public OkResponse<BasketResponse> addBasket(@RequestBody BasketRequest request) throws BasketExistException {
        return OkResponse.of(BasketMapping.getInstance().getResponse().convert(
                basketApiService.addBasket(request)));
    }

    @GetMapping(BasketApiRoutes.BY_ID)
    @ApiOperation(value = "Find basket by ID", notes = "Use this if you need to find a basket.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Basket not found")
    })
    public OkResponse<BasketResponse> byId(
            @ApiParam(value = "Basket ID")
            @PathVariable ObjectId id) throws ChangeSetPersister.NotFoundException {
        return OkResponse.of(BasketMapping.getInstance().getResponse().convert(
                basketApiService.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new)
        ));
    }

    @GetMapping(BasketApiRoutes.ROOT)
    @ApiOperation(value = "Search all cities", notes = "Use this if you want to list all cities.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    public OkResponse<List<BasketResponse>> search(
//            @ModelAttribute SearchRequest request
            ){
        return OkResponse.of(BasketMapping.getInstance().getSearch().convert(
                basketApiService.search()
        ));
    }

    @PutMapping(BasketApiRoutes.ADMIN_BY_ID)
    @ApiOperation(value = "Update basket by ID", notes = "Use this if you want to update basket. You need administrator rights to update a basket.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Basket ID invalid")
    })
    public OkResponse<BasketResponse> updateById(
            @ApiParam(value = "Basket ID")
            @PathVariable String id,
            @RequestBody BasketRequest basketRequest
            ) throws BasketNotExistException {
        return OkResponse.of(BasketMapping.getInstance().getResponse().convert(
                basketApiService.update(basketRequest)
        ));
    }

    @DeleteMapping(BasketApiRoutes.ADMIN_BY_ID)
    @ApiOperation(value = "Delete basket by ID", notes = "Use this if you want to delete basket. You need administrator rights to delete a basket.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    public OkResponse<String> deleteById(
            @ApiParam(value = "Basket ID")
            @PathVariable ObjectId id){
        basketApiService.delete(id);
        return OkResponse.of(HttpStatus.OK.toString());
    }

}

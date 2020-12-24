package com.zelinskiyrk.store.guest.controller;

import com.zelinskiyrk.store.base.api.response.OkResponse;
import com.zelinskiyrk.store.guest.api.request.GuestRequest;
import com.zelinskiyrk.store.guest.api.response.GuestResponse;
import com.zelinskiyrk.store.guest.exception.GuestNotExistException;
import com.zelinskiyrk.store.guest.mapping.GuestMapping;
import com.zelinskiyrk.store.guest.routes.GuestApiRoutes;
import com.zelinskiyrk.store.guest.service.GuestApiService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Api(value = "Guest API")
public class GuestApiController {
    private final GuestApiService guestApiService;

    @PostMapping(GuestApiRoutes.ROOT)
    @ApiOperation(value = "Add new guest", notes = "Используется для добавления данных о покупателе")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Guest already exist")
    })
    public OkResponse<GuestResponse> addGuest(@RequestBody GuestRequest request) {
        return OkResponse.of(GuestMapping.getInstance().getResponse().convert(
                guestApiService.addGuest(request)));
    }

    @GetMapping(GuestApiRoutes.BY_ID)
    @ApiOperation(value = "Find guest by ID", notes = "Use this if you need to find a guest by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Guest not found")
    })
    public OkResponse<GuestResponse> byId(
            @ApiParam(value = "Guest ID")
            @PathVariable ObjectId id) throws ChangeSetPersister.NotFoundException {
        return OkResponse.of(GuestMapping.getInstance().getResponse().convert(
                guestApiService.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new)
        ));
    }

    @PutMapping(GuestApiRoutes.ADMIN_BY_ID)
    @ApiOperation(value = "Update guest by ID", notes = "Use this if you want to update guest. You need administrator rights to update a guest.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Guest ID invalid")
    })
    public OkResponse<GuestResponse> updateById(
            @ApiParam(value = "Guest ID")
            @PathVariable String id,
            @RequestBody GuestRequest guestRequest
    ) throws GuestNotExistException {
        return OkResponse.of(GuestMapping.getInstance().getResponse().convert(
                guestApiService.update(guestRequest)
        ));
    }

    @DeleteMapping(GuestApiRoutes.ADMIN_BY_ID)
    @ApiOperation(value = "Delete guest by ID", notes = "Use this if you want to delete guest. You need administrator rights to delete a guest.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    public OkResponse<String> deleteById(
            @ApiParam(value = "Guest ID")
            @PathVariable ObjectId id) {
        guestApiService.delete(id);
        return OkResponse.of(HttpStatus.OK.toString());
    }
}

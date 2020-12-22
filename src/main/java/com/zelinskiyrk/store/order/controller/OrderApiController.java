package com.zelinskiyrk.store.order.controller;

import com.zelinskiyrk.store.base.api.response.OkResponse;
import com.zelinskiyrk.store.guest.exception.GuestExistException;
import com.zelinskiyrk.store.order.api.request.OrderRequest;
import com.zelinskiyrk.store.order.api.response.OrderResponse;
import com.zelinskiyrk.store.order.exception.OrderNotExistException;
import com.zelinskiyrk.store.order.exception.OutOfTimeException;
import com.zelinskiyrk.store.order.mapping.OrderMapping;
import com.zelinskiyrk.store.order.model.OrderDoc;
import com.zelinskiyrk.store.order.routes.OrderApiRoutes;
import com.zelinskiyrk.store.order.service.OrderApiService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequiredArgsConstructor
@Api(value = "Order API")
public class OrderApiController {
    private final OrderApiService orderApiService;

    @PostMapping(OrderApiRoutes.ROOT)
    @ApiOperation(value = "Add new order", notes = "Use this if you need to add a new order.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Order already exist")
    })
    public OkResponse<OrderResponse> addOrder(@RequestBody OrderRequest request, HttpServletRequest servletRequest) throws GuestExistException, OutOfTimeException {
        return OkResponse.of(OrderMapping.getInstance().getResponse().convert(
                orderApiService.addOrder(request, servletRequest)));
    }

    @GetMapping(OrderApiRoutes.BY_ORDER_NUMBER)
    @ApiOperation(value = "Find order by order number", notes = "Use this if you need to find a order by order number.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Order not found")
    })
    public OkResponse<OrderDoc> byOrderNumber(
            @ApiParam(value = "Order ID")
            @PathVariable Integer orderNumber) throws OrderNotExistException {
        return OkResponse.of(OrderMapping.getInstance().getResponseDocMapping().convert(
                orderApiService.findByOrderNumber(orderNumber)
        ));
    }

    @DeleteMapping(OrderApiRoutes.ADMIN_BY_ID)
    @ApiOperation(value = "Delete order by ID", notes = "Use this if you want to delete order. You need administrator rights to delete a order.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    public OkResponse<String> deleteById(
            @ApiParam(value = "Order ID")
            @PathVariable ObjectId id) {
        orderApiService.delete(id);
        return OkResponse.of(HttpStatus.OK.toString());
    }
}

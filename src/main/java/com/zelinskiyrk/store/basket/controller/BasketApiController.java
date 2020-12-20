package com.zelinskiyrk.store.basket.controller;

import com.zelinskiyrk.store.base.api.response.OkResponse;
import com.zelinskiyrk.store.basket.api.request.BasketRequest;
import com.zelinskiyrk.store.basket.api.response.BasketResponse;
import com.zelinskiyrk.store.basket.exception.EmptyBasketException;
import com.zelinskiyrk.store.basket.exception.ProductBasketExistException;
import com.zelinskiyrk.store.basket.model.BasketDoc;
import com.zelinskiyrk.store.basket.routes.BasketApiRoutes;
import com.zelinskiyrk.store.basket.service.BasketApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequiredArgsConstructor
@Api(value = "Basket API")
public class BasketApiController {
    private final BasketApiService basketApiService;

    @PostMapping(BasketApiRoutes.ROOT)
    @ApiOperation(value = "Add new basket", notes = "Используется для добавления продукта в корзину. Корзина создается во время первого добавления продукта")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
    })
    public OkResponse<BasketResponse> addProduct(@RequestBody BasketRequest request, HttpServletRequest servletRequest) throws ProductBasketExistException {
        BasketResponse basketDoc = basketApiService.addProduct(request, servletRequest);
        return OkResponse.of(basketDoc);
    }


    @PutMapping(BasketApiRoutes.DEL)
    @ApiOperation(value = "Delete product", notes = "Используется для удаления продукта из корзины")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
    })
    public OkResponse<BasketResponse> deleteProduct(@RequestBody BasketRequest request, HttpServletRequest servletRequest) {
        BasketResponse basketDoc = basketApiService.deleteProduct(request, servletRequest);
        return OkResponse.of(basketDoc);
    }

    @PutMapping(BasketApiRoutes.INC)
    @ApiOperation(value = "Increase product quantity", notes = "Увеличить количество продукта на 1")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
    })
    public OkResponse<BasketResponse> increaseProduct(@RequestBody BasketRequest request, HttpServletRequest servletRequest) {
        BasketResponse basketDoc = basketApiService.increaseProduct(request, servletRequest);
        return OkResponse.of(basketDoc);
    }

    @PutMapping(BasketApiRoutes.DEC)
    @ApiOperation(value = "Decrease product quantity", notes = "Уменьшить количество продукта на 1. Когда количество продукта = 0, происходит автоматическое удаление продукта из корзины")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Basket already exist")
    })
    public OkResponse<BasketResponse> decreaseProduct(@RequestBody BasketRequest request, HttpServletRequest servletRequest) {
        BasketResponse basketDoc = basketApiService.decreaseProduct(request, servletRequest);
        return OkResponse.of(basketDoc);
    }


    @GetMapping(BasketApiRoutes.ROOT)
    @ApiOperation(value = "Get basket", notes = "Получение корзины пользователя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    public OkResponse<BasketResponse> getBasket(HttpServletRequest servletRequest, @RequestParam ObjectId cityId) throws EmptyBasketException {
        BasketDoc basketDoc = basketApiService.getBasket(servletRequest);
        if (basketDoc.getProducts().isEmpty()) {
            throw new EmptyBasketException();
        }
        return OkResponse.of(basketApiService.getBasketResponse(basketDoc, cityId));
    }


}

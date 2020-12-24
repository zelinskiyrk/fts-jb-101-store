package com.zelinskiyrk.store.base.controller;

import com.zelinskiyrk.store.base.api.response.ErrorResponse;
import com.zelinskiyrk.store.basket.exception.EmptyBasketException;
import com.zelinskiyrk.store.basket.exception.ProductBasketExistException;
import com.zelinskiyrk.store.basket.exception.ProductBasketNotExistException;
import com.zelinskiyrk.store.category.exception.CategoryExistException;
import com.zelinskiyrk.store.category.exception.CategoryNotExistException;
import com.zelinskiyrk.store.city.exception.CityExistException;
import com.zelinskiyrk.store.city.exception.CityNotExistException;
import com.zelinskiyrk.store.guest.exception.GuestExistException;
import com.zelinskiyrk.store.order.exception.OrderNotExistException;
import com.zelinskiyrk.store.order.exception.OutOfTimeException;
import com.zelinskiyrk.store.photo.exception.PhotoExistException;
import com.zelinskiyrk.store.photo.exception.PhotoNotExistException;
import com.zelinskiyrk.store.product.exception.ProductExistException;
import com.zelinskiyrk.store.product.exception.ProductNotExistException;
import com.zelinskiyrk.store.street.exception.StreetExistException;
import com.zelinskiyrk.store.street.exception.StreetNotExistException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandleApiExceptions extends ResponseEntityExceptionHandler {
    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<Object> notFoundException(ChangeSetPersister.NotFoundException ex, WebRequest request) {
        return buildResponseEntity(ErrorResponse.of("NotFoundException", HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(CityExistException.class)
    public ResponseEntity<Object> CityExistException(CityExistException ex, WebRequest request) {
        return buildResponseEntity(ErrorResponse.of("CityExistException", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(CityNotExistException.class)
    public ResponseEntity<Object> CityNotExistException(CityNotExistException ex, WebRequest request) {
        return buildResponseEntity(ErrorResponse.of("CityNotExistException", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(StreetExistException.class)
    public ResponseEntity<Object> StreetExistException(StreetExistException ex, WebRequest request) {
        return buildResponseEntity(ErrorResponse.of("StreetExistException", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(StreetNotExistException.class)
    public ResponseEntity<Object> StreetNotExistException(StreetNotExistException ex, WebRequest request) {
        return buildResponseEntity(ErrorResponse.of("StreetNotExistException", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(CategoryExistException.class)
    public ResponseEntity<Object> CategoryExistException(CategoryExistException ex, WebRequest request) {
        return buildResponseEntity(ErrorResponse.of("CategoryExistException", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(CategoryNotExistException.class)
    public ResponseEntity<Object> CategoryNotExistException(CategoryNotExistException ex, WebRequest request) {
        return buildResponseEntity(ErrorResponse.of("CategoryNotExistException", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ProductExistException.class)
    public ResponseEntity<Object> ProductExistException(ProductExistException ex, WebRequest request) {
        return buildResponseEntity(ErrorResponse.of("ProductExistException", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<Object> ProductNotExistException(ProductNotExistException ex, WebRequest request) {
        return buildResponseEntity(ErrorResponse.of("ProductNotExistException", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(PhotoExistException.class)
    public ResponseEntity<Object> PhotoExistException(PhotoExistException ex, WebRequest request) {
        return buildResponseEntity(ErrorResponse.of("PhotoExistException", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(PhotoNotExistException.class)
    public ResponseEntity<Object> PhotoNotExistException(PhotoNotExistException ex, WebRequest request) {
        return buildResponseEntity(ErrorResponse.of("PhotoNotExistException", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ProductBasketExistException.class)
    public ResponseEntity<Object> ProductBasketExistException(ProductBasketExistException ex, WebRequest request) {
        return buildResponseEntity(ErrorResponse.of("The product with this ID is already in the cart", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ProductBasketNotExistException.class)
    public ResponseEntity<Object> ProductBasketNotExistException(ProductBasketNotExistException ex, WebRequest request) {
        return buildResponseEntity(ErrorResponse.of("Product with this ID is not in the cart", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(EmptyBasketException.class)
    public ResponseEntity<Object> EmptyBasketException(EmptyBasketException ex, WebRequest request) {
        return buildResponseEntity(ErrorResponse.of("Basket is empty", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(GuestExistException.class)
    public ResponseEntity<Object> GuestExistException(GuestExistException ex, WebRequest request) {
        return buildResponseEntity(ErrorResponse.of("This guest already exist", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(OrderNotExistException.class)
    public ResponseEntity<Object> OrderNotExistException(OrderNotExistException ex, WebRequest request) {
        return buildResponseEntity(ErrorResponse.of("Order with this order number does not exist", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(OutOfTimeException.class)
    public ResponseEntity<Object> OutOfTimeException(OutOfTimeException ex, WebRequest request) {
        return buildResponseEntity(ErrorResponse.of("The selected delivery time is not possible. Choose another time", HttpStatus.BAD_REQUEST));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> Exception(Exception ex, WebRequest request) {
        return buildResponseEntity(ErrorResponse.of("Exception", HttpStatus.INTERNAL_SERVER_ERROR));
    }
}

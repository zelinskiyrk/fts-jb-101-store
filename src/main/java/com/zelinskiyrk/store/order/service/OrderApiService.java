package com.zelinskiyrk.store.order.service;

import com.zelinskiyrk.store.basket.api.response.BasketResponse;
import com.zelinskiyrk.store.basket.model.BasketDoc;
import com.zelinskiyrk.store.basket.service.BasketApiService;
import com.zelinskiyrk.store.city.service.CityApiService;
import com.zelinskiyrk.store.guest.api.request.GuestRequest;
import com.zelinskiyrk.store.guest.exception.GuestExistException;
import com.zelinskiyrk.store.guest.model.GuestDoc;
import com.zelinskiyrk.store.guest.service.GuestApiService;
import com.zelinskiyrk.store.order.api.request.OrderRequest;
import com.zelinskiyrk.store.order.exception.OrderNotExistException;
import com.zelinskiyrk.store.order.exception.OutOfTimeException;
import com.zelinskiyrk.store.order.model.OrderDoc;
import com.zelinskiyrk.store.order.model.PaymentMethod;
import com.zelinskiyrk.store.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderApiService {
    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;
    private final BasketApiService basketApiService;
    private final GuestApiService guestApiService;
    private final CityApiService cityApiService;
    private static final Long DELAY_ON_ORDER = 10L;


    public OrderDoc addOrder(OrderRequest request, HttpServletRequest servletRequest)
            throws GuestExistException, OutOfTimeException {

        List<OrderDoc.Product> orderProductList = getOrderProductList(request, servletRequest);

        String guestId = getGuestId(request);

        LocalDateTime deliveryTime = getDeliveryTime(request);

        PaymentMethod paymentMethod = request.getPaymentMethod();

        Double orderPrice = getOrderPrice(request, servletRequest);

        OrderDoc orderDoc = OrderDoc.builder()
                .guestId(guestId)
                .orderNumber(getOrderNumber() + 1)
                .productList(orderProductList)
                .deliveryTime(deliveryTime)
                .paymentMethod(paymentMethod)
                .orderPrice(orderPrice)
                .build();

        orderRepository.save(orderDoc);
        return orderDoc;
    }

    public List<OrderDoc.Product> getOrderProductList(OrderRequest request, HttpServletRequest servletRequest) {
        BasketDoc basketDoc = basketApiService.getBasket(servletRequest);
        BasketResponse basketResponse = basketApiService.getBasketResponse(basketDoc, request.getCityId());
        List<BasketResponse.Product> basketProductList = basketResponse.getProducts();

        return basketProductList.stream()
                .map(item -> OrderDoc.Product.builder()
                        .productId(item.getProductId())
                        .productName(item.getProductName())
                        .photoId(item.getPhotoId())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .build()).collect(Collectors.toList());
    }

    public String getGuestId(OrderRequest request) {
        GuestRequest guestRequest = request.getGuestRequest();
        GuestDoc guest = guestApiService.addGuest(guestRequest);
        return guest.getId().toString();
    }

    public LocalDateTime getDeliveryTime(OrderRequest request) throws OutOfTimeException {
        return checkDeliveryTime(request.getDeliveryTime(), request.getCityId());
    }

    public LocalDateTime checkDeliveryTime(LocalDateTime deliveryTime, ObjectId cityId) throws OutOfTimeException {
        Long cityDeliveryTime = Long.valueOf(
                cityApiService.findDeliveryTime(cityId)
        );

        LocalDateTime serverTime = LocalDateTime.now().withNano(0);

        if (deliveryTime.isBefore(
                serverTime.plusMinutes(cityDeliveryTime).plusMinutes(DELAY_ON_ORDER))
        ) {
            throw new OutOfTimeException();
        }
        return deliveryTime;
    }

    public Double getOrderPrice(OrderRequest request, HttpServletRequest servletRequest) {
        BasketDoc basketDoc = basketApiService.getBasket(servletRequest);
        BasketResponse basketResponse = basketApiService.getBasketResponse(basketDoc, request.getCityId());
        return basketResponse.getTotalPrice();
    }

    public Integer getOrderNumber() {
        return (int) mongoTemplate.getCollection("orderDoc").countDocuments();
    }


    public OrderDoc findByOrderNumber(Integer orderNumber) throws OrderNotExistException {
        if (orderRepository.findByOrderNumber(orderNumber) == null) {
            throw new OrderNotExistException();
        }
        return orderRepository.findByOrderNumber(orderNumber);
    }


    public void delete(ObjectId id) {
        orderRepository.deleteById(id);
    }
}

package com.zelinskiyrk.store.order.api.request;

import com.zelinskiyrk.store.basket.api.response.BasketResponse;
import com.zelinskiyrk.store.guest.api.request.GuestRequest;
import com.zelinskiyrk.store.order.model.PaymentMethod;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel(value = "OrderRequest", description = "Model for create order")
public class OrderRequest {
    private ObjectId id;
    private ObjectId cityId;
    private BasketResponse basketResponse;
    private GuestRequest guestRequest;
    private LocalDateTime deliveryTime;
    private PaymentMethod paymentMethod;
}

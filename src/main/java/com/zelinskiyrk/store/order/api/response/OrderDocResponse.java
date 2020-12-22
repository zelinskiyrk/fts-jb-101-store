package com.zelinskiyrk.store.order.api.response;

import com.zelinskiyrk.store.order.model.OrderDoc;
import com.zelinskiyrk.store.order.model.PaymentMethod;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@ApiModel(value = "OrderResponse", description = "Order data (for search and List)")
public class OrderDocResponse {
    protected Integer orderNumber;
    protected List<OrderDoc.Product> productList;
    protected String guestId;
    protected LocalDateTime deliveryTime;
    protected PaymentMethod paymentMethod;
    protected Double orderPrice;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    @ApiModel(value = "Product", description = "Класс, описывающий отдельный продукт в списке продуктов заказа")
    public static class Product {
        protected String productId;
        protected String productName;
        protected String photoId;
        protected Integer quantity;
        protected Double price;
    }
}

package com.zelinskiyrk.store.order.model;

import io.swagger.annotations.ApiModel;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDoc {
    @Id
    private ObjectId id;
    private Integer orderNumber;
    private List<Product> productList;
    private String guestId;
    private LocalDateTime deliveryTime;
    private PaymentMethod paymentMethod;
    private Double orderPrice;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    @ApiModel(value = "Product", description = "Класс, описывающий отдельный продукт в списке продуктов заказа")
    public static class Product {
        private String productId;
        private String productName;
        private String photoId;
        private Integer quantity;
        private Double price;
    }
}

package com.zelinskiyrk.store.basket.api.response;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "BasketResponse", description = "Basket data (for search and List)")
public class BasketResponse {
    private List<Product> products;
    private Double orderPrice;
    private Double deliveryPrice;
    private Double totalPrice;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    @ApiModel(value = "Product", description = "Класс, описывающий отдельный продукт в списке продуктов корзины")
    public static class Product {
        private String productId;
        private String productName;
        private String photoId;
        private Integer quantity;
        private Double price;
    }

}


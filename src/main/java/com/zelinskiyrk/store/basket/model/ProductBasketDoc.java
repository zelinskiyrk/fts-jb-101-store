package com.zelinskiyrk.store.basket.model;

import lombok.*;

@Getter
@Setter
@NonNull
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductBasketDoc {
    private String productId;
    private Integer quantity;
}

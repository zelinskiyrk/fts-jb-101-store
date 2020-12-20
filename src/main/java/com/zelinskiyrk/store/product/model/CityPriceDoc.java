package com.zelinskiyrk.store.product.model;

import lombok.*;

@Getter
@Setter
@NonNull
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CityPriceDoc {
    private String cityId;
    @NonNull
    private Double price;
}
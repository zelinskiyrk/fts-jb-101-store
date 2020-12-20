package com.zelinskiyrk.store.product.model;

import lombok.*;

@Getter
@Setter
@NonNull
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityPriceModel {
    private String cityId;
    @NonNull
    private Double price;
}
package com.zelinskiyrk.store.product.api;

import lombok.*;

@Getter
@Setter
@NonNull
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityPriceModel {
    private String cityId;
    @NonNull private Integer price;
}
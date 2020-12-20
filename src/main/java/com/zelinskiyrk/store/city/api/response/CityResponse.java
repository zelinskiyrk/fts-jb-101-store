package com.zelinskiyrk.store.city.api.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@ApiModel(value = "CityResponse", description = "City data (for search and List)")
public class CityResponse {
    protected String id;
    protected String cityName;
    private String deliveryTime;
    private String deliveryPrice;
}

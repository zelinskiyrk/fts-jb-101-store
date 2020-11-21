package com.zelinskiyrk.store.city.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "AddCityRequest", description = "Model for add city")
public class AddCityRequest {
    private String cityName;
}

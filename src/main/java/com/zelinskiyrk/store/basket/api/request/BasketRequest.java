package com.zelinskiyrk.store.basket.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@ApiModel(value = "BasketRequest", description = "Model for create basket")
public class BasketRequest {
    private String productId;
    private ObjectId cityId;
}


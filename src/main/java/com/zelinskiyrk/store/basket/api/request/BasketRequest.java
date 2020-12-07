package com.zelinskiyrk.store.basket.api.request;

import com.zelinskiyrk.store.product.model.ProductDoc;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@ApiModel(value = "BasketRequest", description = "Model for create and update basket")
public class BasketRequest {
                private String productId;
}


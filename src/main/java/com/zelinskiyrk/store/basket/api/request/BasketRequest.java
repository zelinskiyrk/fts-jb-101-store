package com.zelinskiyrk.store.basket.api.request;

import com.zelinskiyrk.store.product.model.ProductDoc;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Optional;

@Getter
@Setter
@ApiModel(value = "BasketRequest", description = "Model for create and update basket")
public class BasketRequest {
                private ObjectId id;
                private String productId;
                private String cityId;
                private ArrayList product;
                private Integer productQuantity;
                private Integer orderPrice;
                private Integer deliveryPrice;
                private Integer totalPrice;
}

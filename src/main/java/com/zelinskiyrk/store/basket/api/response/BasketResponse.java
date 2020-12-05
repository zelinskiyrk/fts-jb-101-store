package com.zelinskiyrk.store.basket.api.response;

import com.zelinskiyrk.store.product.model.ProductDoc;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Optional;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@ApiModel(value = "BasketResponse", description = "Basket data (for search and List)")
public class BasketResponse {
                protected String id;
                private ArrayList product;
                protected String productQuantity;
                protected String orderPrice;
                protected String deliveryPrice;
                protected String totalPrice;
                private String sessionId;
}

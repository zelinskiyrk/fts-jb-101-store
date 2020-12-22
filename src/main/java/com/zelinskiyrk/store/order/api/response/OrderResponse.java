package com.zelinskiyrk.store.order.api.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@ApiModel(value = "OrderResponse", description = "Order data (for search and List)")
public class OrderResponse {
    protected Integer orderCount;
}

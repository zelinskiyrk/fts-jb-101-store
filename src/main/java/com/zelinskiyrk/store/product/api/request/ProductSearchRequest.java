package com.zelinskiyrk.store.product.api.request;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class ProductSearchRequest {
    @ApiParam(name = "productName", value = "Search products by name", required = false)
    protected String productName = null;
}

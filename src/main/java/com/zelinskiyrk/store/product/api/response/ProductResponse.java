package com.zelinskiyrk.store.product.api.response;

import com.zelinskiyrk.store.product.model.CityPriceModel;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@ApiModel(value = "ProductResponse", description = "Product data (for search and List)")
public class ProductResponse {
    protected String id;
    protected String categoryId;
    protected String photoId;
    protected String productName;
    protected String description;
    protected List<CityPriceModel> prices;
    protected Float proteins;
    protected Float fats;
    protected Float carbohydrates;
    protected Float calories;
}

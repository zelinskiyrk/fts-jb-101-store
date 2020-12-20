package com.zelinskiyrk.store.product.api.request;

import com.zelinskiyrk.store.product.model.CityPriceModel;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.ArrayList;


@Getter
@Setter
@ApiModel(value = "ProductRequest", description = "Model for create and update product")
public class ProductRequest {
    private ObjectId id;
    private ObjectId categoryId;
    private ObjectId photoId;
    private String productName;
    private String description;
    private Float proteins;
    private Float fats;
    private Float carbohydrates;
    private Float calories;
    private ArrayList<CityPriceModel> prices = new ArrayList<CityPriceModel>();
}

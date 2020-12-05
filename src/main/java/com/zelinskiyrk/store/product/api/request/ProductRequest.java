package com.zelinskiyrk.store.product.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
@Setter
@ApiModel(value = "ProductRequest", description = "Model for create and update product")
public class ProductRequest {
                private ObjectId id;
                private ObjectId categoryId;
                private ObjectId photoId;
                private String productName;
                private String description;
                private float proteins;
                private float fats;
                private float carbohydrates;
                private float calories;
                private ArrayList prices = new ArrayList();
}

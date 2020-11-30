package com.zelinskiyrk.store.product.api.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;

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
                protected ArrayList prices;
                protected Integer proteins;
                protected Integer fats;
                protected Integer carbohydrates;
                protected Integer calories;
}

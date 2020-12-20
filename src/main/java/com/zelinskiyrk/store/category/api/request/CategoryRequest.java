package com.zelinskiyrk.store.category.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@ApiModel(value = "CategoryRequest", description = "Model for create and update products category")
public class CategoryRequest {
    private ObjectId id;
    private String categoryName;
}

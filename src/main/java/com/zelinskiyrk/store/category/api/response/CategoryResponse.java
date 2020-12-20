package com.zelinskiyrk.store.category.api.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@ApiModel(value = "CategoryResponse", description = "Category data (for search and List)")
public class CategoryResponse {
    protected String id;
    protected String categoryName;
}

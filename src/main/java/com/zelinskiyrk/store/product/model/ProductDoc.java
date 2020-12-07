package com.zelinskiyrk.store.product.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Document
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDoc {
    @Id
                private ObjectId id;
                private ObjectId categoryId;
                private String cityId;
                private ObjectId photoId;
                private String productName;
                private String description;
                private List<CityPriceDoc> prices;
                private float proteins;
                private float fats;
                private float carbohydrates;
                private float calories;
}

package com.zelinskiyrk.store.product.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;

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
                private ArrayList prices;
                private Integer proteins;
                private Integer fats;
                private Integer carbohydrates;
                private Integer calories;
}

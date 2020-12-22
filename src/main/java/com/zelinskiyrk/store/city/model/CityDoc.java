package com.zelinskiyrk.store.city.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDoc {
    @Id
    private ObjectId id;
    private String cityName;
    private Integer deliveryTime;
    private Double deliveryPrice;
}

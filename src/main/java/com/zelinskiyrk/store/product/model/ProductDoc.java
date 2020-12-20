package com.zelinskiyrk.store.product.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private ObjectId photoId;
    private String productName;
    private String description;
    private List<CityPriceDoc> prices;
    private Float proteins;
    private Float fats;
    private Float carbohydrates;
    private Float calories;

    public Double getPriceByCityId(String cityId) {

        for (CityPriceDoc price : getPrices()) {
            if (price.getCityId().equals(cityId)) {
                return price.getPrice();
            }
        }
        return null;
    }

}

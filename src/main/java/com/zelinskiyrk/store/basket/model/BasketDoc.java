package com.zelinskiyrk.store.basket.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketDoc {
    @Id
    private ObjectId id;
    private List<ProductBasketDoc> products = new ArrayList<ProductBasketDoc>();
    private String sessionId;
    private Integer orderPrice;
    private Integer deliveryPrice;
    private Integer totalPrice;
}

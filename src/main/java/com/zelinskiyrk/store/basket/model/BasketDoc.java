package com.zelinskiyrk.store.basket.model;

import com.zelinskiyrk.store.product.model.ProductDoc;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private List<ProductBasketDoc> products = new ArrayList();
    private String sessionId;


    //                private String productId;

//                private Integer productQuantity;
//                private Integer orderPrice;
//                private Integer deliveryPrice;
//                private Integer totalPrice;
//                private String sessionId;
}

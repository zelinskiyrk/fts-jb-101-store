package com.zelinskiyrk.store.street.model;

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
public class StreetDoc {
    @Id
    private ObjectId id;
    private String streetName;
    private ObjectId cityId;
}

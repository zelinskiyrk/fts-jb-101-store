package com.zelinskiyrk.store.photo.model;

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
public class PhotoDoc {
    @Id
    private ObjectId id;
    private String photoName;
    private String contentType;
}

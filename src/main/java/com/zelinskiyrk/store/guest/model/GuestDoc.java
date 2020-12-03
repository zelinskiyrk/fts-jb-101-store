package com.zelinskiyrk.store.guest.model;

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
public class GuestDoc {
    @Id
                private ObjectId id;
                private String guestName;
                private String guestPhoneNumber;
                private Address address;
}

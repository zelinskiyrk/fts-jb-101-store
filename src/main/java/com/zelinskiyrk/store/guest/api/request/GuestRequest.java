package com.zelinskiyrk.store.guest.api.request;

import com.zelinskiyrk.store.guest.model.Address;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@ApiModel(value = "GuestRequest", description = "Model for create and update guest")
public class GuestRequest {
                private ObjectId id;
                private String guestName;
                private String guestPhoneNumber;
                private Address address;
}

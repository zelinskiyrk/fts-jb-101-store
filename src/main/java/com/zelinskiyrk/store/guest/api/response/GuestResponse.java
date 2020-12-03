package com.zelinskiyrk.store.guest.api.response;

import com.zelinskiyrk.store.guest.model.Address;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@ApiModel(value = "GuestResponse", description = "Guest data (for search and List)")
public class GuestResponse {
                protected String id;
                protected String guestName;
                protected String guestPhoneNumber;
                protected Address address;
}

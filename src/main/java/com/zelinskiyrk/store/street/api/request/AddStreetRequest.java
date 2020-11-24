package com.zelinskiyrk.store.street.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@ApiModel(value = "AddStreetRequest", description = "Model for add street")
public class AddStreetRequest {
        private ObjectId id;
        private String streetName;
        private ObjectId cityId;
}

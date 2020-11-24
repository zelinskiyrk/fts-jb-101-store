package com.zelinskiyrk.store.street.api.response;

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
@ApiModel(value = "StreetResponse", description = "Street data (for search and List)")
public class StreetResponse {
        protected String id;
        protected String streetName;
        protected String cityId;
}

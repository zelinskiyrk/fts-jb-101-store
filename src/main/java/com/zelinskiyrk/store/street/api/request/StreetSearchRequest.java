package com.zelinskiyrk.store.street.api.request;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class StreetSearchRequest {
    @ApiParam(name = "cityId", value = "Search streets by city", required = true)
    protected ObjectId cityId = null;
}

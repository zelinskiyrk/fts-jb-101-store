package com.zelinskiyrk.store.street.api.request;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StreetSearchRequest {
    //    @ApiParam(name = "cityId", value = "Search streets by city", required = true)
//    protected ObjectId cityId = null;
//    @ApiParam(name = "streetName", value = "Search streets by streetName", required = false)
//    protected String streetName = null;
    @ApiParam(name = "query", value = "Search by fields", required = false)
    protected String query = null;
}

package com.zelinskiyrk.store.street.api.request;

import com.zelinskiyrk.store.base.api.request.SearchRequest;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
//@SuperBuilder
public class StreetSearchRequest extends SearchRequest {
    @ApiParam(name = "cityId", value = "Search streets by city", required = true)
    protected ObjectId cityId;
    @ApiParam(name = "streetName", value = "Search streets by streetName")
    protected String streetName;
//    @ApiParam(name = "query", value = "Search by fields", required = false)
//    protected String query = null;
}

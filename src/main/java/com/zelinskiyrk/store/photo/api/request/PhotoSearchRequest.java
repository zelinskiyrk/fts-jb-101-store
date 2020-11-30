package com.zelinskiyrk.store.photo.api.request;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class PhotoSearchRequest {
    @ApiParam(name = "photoName", value = "Search photos by photoName", required = true)
    protected String photoName = null;
}

package com.zelinskiyrk.store.photo.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@ApiModel(value = "PhotoRequest", description = "Model for create and update photo")
public class PhotoRequest {
                private ObjectId id;
                private String photoName;
                private String contentType;
}

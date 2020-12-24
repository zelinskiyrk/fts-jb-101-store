package com.zelinskiyrk.store.base.api.request;

import io.swagger.annotations.ApiParam;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {
    @ApiParam(name = "query", value = "Search by fields")
    protected String query = null;
}

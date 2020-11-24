package com.zelinskiyrk.store.street.mapping;

import com.zelinskiyrk.store.base.api.response.SearchResponse;
import com.zelinskiyrk.store.base.mapping.BaseMapping;
import com.zelinskiyrk.store.street.api.request.StreetRequest;
import com.zelinskiyrk.store.street.api.response.StreetResponse;
import com.zelinskiyrk.store.street.model.StreetDoc;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StreetMapping {

    public static class RequestMapping extends BaseMapping<StreetRequest, StreetDoc> {

        @Override
        public StreetDoc convert(StreetRequest streetRequest){
            return StreetDoc.builder()
                    .id(streetRequest.getId())
                    .streetName(streetRequest.getStreetName())
                    .cityId(streetRequest.getCityId())
                    .build();
        }

        @Override
        public StreetRequest unmapping(StreetDoc streetDoc) {
            throw new RuntimeException("don't use this");
        }
    }

    public static class ResponseMapping extends BaseMapping<StreetDoc, StreetResponse> {

        @Override
        public StreetResponse convert(StreetDoc streetDoc){
            return StreetResponse.builder()
                    .id(streetDoc.getId().toString())
                    .streetName(streetDoc.getStreetName())
                    .cityId(streetDoc.getCityId().toString())
                    .build();
        }

        @Override
        public StreetDoc unmapping(StreetResponse streetResponse) {
            throw new RuntimeException("don't use this");
        }
    }

    public static class SearchMapping extends BaseMapping<SearchResponse<StreetDoc>, SearchResponse<StreetResponse>>{
        private ResponseMapping responseMapping = new ResponseMapping();

        @Override
        public SearchResponse<StreetResponse> convert(SearchResponse<StreetDoc> searchResponse) {
            return SearchResponse.of(
                    searchResponse.getList().stream().map(responseMapping::convert).collect(Collectors.toList()),
                    searchResponse.getCount()
            );
        }

        @Override
        public SearchResponse<StreetDoc> unmapping(SearchResponse<StreetResponse> streetResponses) {
            throw new RuntimeException("don't use this");
        }
    }

    private final RequestMapping request = new RequestMapping();
    private final ResponseMapping response = new ResponseMapping();
    private final SearchMapping search = new SearchMapping();

    public static StreetMapping getInstance(){
        return new StreetMapping();
    }
}

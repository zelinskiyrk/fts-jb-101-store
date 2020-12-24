package com.zelinskiyrk.store.city.mapping;

import com.zelinskiyrk.store.base.mapping.BaseMapping;
import com.zelinskiyrk.store.city.api.request.CityRequest;
import com.zelinskiyrk.store.city.api.response.CityResponse;
import com.zelinskiyrk.store.city.model.CityDoc;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CityMapping {

    public static class RequestMapping extends BaseMapping<CityRequest, CityDoc> {

        @Override
        public CityDoc convert(CityRequest cityRequest) {
            return CityDoc.builder()
                    .id(cityRequest.getId())
                    .cityName(cityRequest.getCityName())
                    .deliveryTime(cityRequest.getDeliveryTime())
                    .deliveryPrice(cityRequest.getDeliveryPrice())
                    .build();
        }

        @Override
        public CityRequest unmapping(CityDoc cityDoc) {
            throw new RuntimeException("don't use this");
        }
    }

    public static class ResponseMapping extends BaseMapping<CityDoc, CityResponse> {

        @Override
        public CityResponse convert(CityDoc cityDoc) {
            return CityResponse.builder()
                    .id(cityDoc.getId().toString())
                    .cityName(cityDoc.getCityName())
                    .deliveryTime(cityDoc.getDeliveryTime().toString())
                    .deliveryPrice(cityDoc.getDeliveryPrice().toString())
                    .build();
        }

        @Override
        public CityDoc unmapping(CityResponse cityResponse) {
            throw new RuntimeException("don't use this");
        }
    }

    public static class SearchMapping extends BaseMapping<List<CityDoc>, List<CityResponse>> {
        private final ResponseMapping responseMapping = new ResponseMapping();

        @Override
        public List<CityResponse> convert(List<CityDoc> cityDocs) {
            return cityDocs.stream().map(responseMapping::convert).collect(Collectors.toList());
        }

        @Override
        public List<CityDoc> unmapping(List<CityResponse> cityResponses) {
            throw new RuntimeException("don't use this");
        }
    }

    private final RequestMapping request = new RequestMapping();
    private final ResponseMapping response = new ResponseMapping();
    private final SearchMapping search = new SearchMapping();

    public static CityMapping getInstance() {
        return new CityMapping();
    }
}

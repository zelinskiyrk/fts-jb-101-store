package com.zelinskiyrk.store.guest.mapping;

import com.zelinskiyrk.store.base.api.response.SearchResponse;
import com.zelinskiyrk.store.base.mapping.BaseMapping;
import com.zelinskiyrk.store.guest.api.request.GuestRequest;
import com.zelinskiyrk.store.guest.api.response.GuestResponse;
import com.zelinskiyrk.store.guest.model.GuestDoc;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GuestMapping {

    public static class RequestMapping extends BaseMapping<GuestRequest, GuestDoc> {

        @Override
        public GuestDoc convert(GuestRequest guestRequest){
            return GuestDoc.builder()
                        .id(guestRequest.getId())
                        .guestName(guestRequest.getGuestName())
                        .guestPhoneNumber(guestRequest.getGuestPhoneNumber())
                        .address(guestRequest.getAddress())
                    .build();
        }

        @Override
        public GuestRequest unmapping(GuestDoc guestDoc) {
            throw new RuntimeException("don't use this");
        }
    }

    public static class ResponseMapping extends BaseMapping<GuestDoc, GuestResponse> {

        @Override
        public GuestResponse convert(GuestDoc guestDoc){
            return GuestResponse.builder()
                        .id(guestDoc.getId().toString())
                        .guestName(guestDoc.getGuestName())
                        .guestPhoneNumber(guestDoc.getGuestPhoneNumber())
                        .address(guestDoc.getAddress())
                    .build();
        }

        @Override
        public GuestDoc unmapping(GuestResponse guestResponse) {
            throw new RuntimeException("don't use this");
        }
    }

    public static class SearchMapping extends BaseMapping<List<GuestDoc>, List<GuestResponse>>{
        private ResponseMapping responseMapping = new ResponseMapping();

        @Override
        public List<GuestResponse> convert(List<GuestDoc> guestDocs) {
            return guestDocs.stream().map(responseMapping::convert).collect(Collectors.toList());
        }

        @Override
        public List<GuestDoc> unmapping(List<GuestResponse> guestResponses) {
            throw new RuntimeException("don't use this");
        }
    }

    private final RequestMapping request = new RequestMapping();
    private final ResponseMapping response = new ResponseMapping();
    private final SearchMapping search = new SearchMapping();

    public static GuestMapping getInstance(){
        return new GuestMapping();
    }
}

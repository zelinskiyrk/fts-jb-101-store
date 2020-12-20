package com.zelinskiyrk.store.photo.mapping;

import com.zelinskiyrk.store.base.api.response.SearchResponse;
import com.zelinskiyrk.store.base.mapping.BaseMapping;
import com.zelinskiyrk.store.photo.api.request.PhotoRequest;
import com.zelinskiyrk.store.photo.api.response.PhotoResponse;
import com.zelinskiyrk.store.photo.model.PhotoDoc;
import lombok.Getter;

import java.util.stream.Collectors;

@Getter
public class PhotoMapping {

    public static class RequestMapping extends BaseMapping<PhotoRequest, PhotoDoc> {

        @Override
        public PhotoDoc convert(PhotoRequest photoRequest) {
            return PhotoDoc.builder()
                    .id(photoRequest.getId())
                    .photoName(photoRequest.getPhotoName())
                    .build();
        }

        @Override
        public PhotoRequest unmapping(PhotoDoc photoDoc) {
            throw new RuntimeException("don't use this");
        }
    }

    public static class ResponseMapping extends BaseMapping<PhotoDoc, PhotoResponse> {

        @Override
        public PhotoResponse convert(PhotoDoc photoDoc) {
            return PhotoResponse.builder()
                    .id(photoDoc.getId().toString())
                    .photoName(photoDoc.getPhotoName())
                    .build();
        }

        @Override
        public PhotoDoc unmapping(PhotoResponse photoResponse) {
            throw new RuntimeException("don't use this");
        }
    }

    public static class SearchMapping extends BaseMapping<SearchResponse<PhotoDoc>, SearchResponse<PhotoResponse>> {
        private final ResponseMapping responseMapping = new ResponseMapping();

        @Override
        public SearchResponse<PhotoResponse> convert(SearchResponse<PhotoDoc> searchResponse) {
            return SearchResponse.of(
                    searchResponse.getList().stream().map(responseMapping::convert).collect(Collectors.toList()),
                    searchResponse.getCount()
            );
        }

        @Override
        public SearchResponse<PhotoDoc> unmapping(SearchResponse<PhotoResponse> photoResponses) {
            throw new RuntimeException("don't use this");
        }
    }

    private final RequestMapping request = new RequestMapping();
    private final ResponseMapping response = new ResponseMapping();
    private final SearchMapping search = new SearchMapping();

    public static PhotoMapping getInstance() {
        return new PhotoMapping();
    }
}

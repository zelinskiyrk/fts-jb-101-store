package com.zelinskiyrk.store.category.mapping;

import com.zelinskiyrk.store.base.mapping.BaseMapping;
import com.zelinskiyrk.store.category.api.request.CategoryRequest;
import com.zelinskiyrk.store.category.api.response.CategoryResponse;
import com.zelinskiyrk.store.category.model.CategoryDoc;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CategoryMapping {

    public static class RequestMapping extends BaseMapping<CategoryRequest, CategoryDoc> {

        @Override
        public CategoryDoc convert(CategoryRequest categoryRequest) {
            return CategoryDoc.builder()
                    .id(categoryRequest.getId())
                    .categoryName(categoryRequest.getCategoryName())
                    .build();
        }

        @Override
        public CategoryRequest unmapping(CategoryDoc categoryDoc) {
            throw new RuntimeException("don't use this");
        }
    }

    public static class ResponseMapping extends BaseMapping<CategoryDoc, CategoryResponse> {

        @Override
        public CategoryResponse convert(CategoryDoc categoryDoc) {
            return CategoryResponse.builder()
                    .id(categoryDoc.getId().toString())
                    .categoryName(categoryDoc.getCategoryName())
                    .build();
        }

        @Override
        public CategoryDoc unmapping(CategoryResponse categoryResponse) {
            throw new RuntimeException("don't use this");
        }
    }

    public static class SearchMapping extends BaseMapping<List<CategoryDoc>, List<CategoryResponse>> {
        private final ResponseMapping responseMapping = new ResponseMapping();

        @Override
        public List<CategoryResponse> convert(List<CategoryDoc> categoryDocs) {
            return categoryDocs.stream().map(responseMapping::convert).collect(Collectors.toList());
        }

        @Override
        public List<CategoryDoc> unmapping(List<CategoryResponse> categoryResponses) {
            throw new RuntimeException("don't use this");
        }
    }

    private final RequestMapping request = new RequestMapping();
    private final ResponseMapping response = new ResponseMapping();
    private final SearchMapping search = new SearchMapping();

    public static CategoryMapping getInstance() {
        return new CategoryMapping();
    }
}

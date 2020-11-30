package com.zelinskiyrk.store.product.mapping;

import com.zelinskiyrk.store.base.api.response.SearchResponse;
import com.zelinskiyrk.store.base.mapping.BaseMapping;
import com.zelinskiyrk.store.product.api.request.ProductRequest;
import com.zelinskiyrk.store.product.api.response.ProductResponse;
import com.zelinskiyrk.store.product.model.ProductDoc;
import lombok.Getter;

import java.util.stream.Collectors;

@Getter
public class ProductMapping {

    public static class RequestMapping extends BaseMapping<ProductRequest, ProductDoc> {

        @Override
        public ProductDoc convert(ProductRequest productRequest){
            return ProductDoc.builder()
                        .id(productRequest.getId())
                        .categoryId(productRequest.getCategoryId())
                        .photoId(productRequest.getPhotoId())
                        .productName(productRequest.getProductName())
                        .description(productRequest.getDescription())
                        .prices(productRequest.getPrices())
                        .proteins(productRequest.getProteins())
                        .fats(productRequest.getFats())
                        .carbohydrates(productRequest.getCarbohydrates())
                        .calories(productRequest.getCalories())
                    .build();
        }

        @Override
        public ProductRequest unmapping(ProductDoc productDoc) {
            throw new RuntimeException("don't use this");
        }
    }

    public static class ResponseMapping extends BaseMapping<ProductDoc, ProductResponse> {

        @Override
        public ProductResponse convert(ProductDoc productDoc){
            return ProductResponse.builder()
                        .id(productDoc.getId().toString())
                        .categoryId(productDoc.getCategoryId().toString())
                        .photoId(productDoc.getPhotoId().toString())
                        .productName(productDoc.getProductName())
                        .description(productDoc.getDescription())
                        .prices(productDoc.getPrices())
                        .proteins(productDoc.getProteins())
                        .fats(productDoc.getFats())
                        .carbohydrates(productDoc.getCarbohydrates())
                        .calories(productDoc.getCalories())
                    .build();
        }

        @Override
        public ProductDoc unmapping(ProductResponse productResponse) {
            throw new RuntimeException("don't use this");
        }
    }

    public static class SearchMapping extends BaseMapping<SearchResponse<ProductDoc>, SearchResponse<ProductResponse>>{
        private ResponseMapping responseMapping = new ResponseMapping();

        @Override
        public SearchResponse<ProductResponse> convert(SearchResponse<ProductDoc> searchResponse) {
            return SearchResponse.of(
                    searchResponse.getList().stream().map(responseMapping::convert).collect(Collectors.toList()),
                    searchResponse.getCount()
            );
        }

        @Override
        public SearchResponse<ProductDoc> unmapping(SearchResponse<ProductResponse> productResponses) {
            throw new RuntimeException("don't use this");
        }
    }

    private final RequestMapping request = new RequestMapping();
    private final ResponseMapping response = new ResponseMapping();
    private final SearchMapping search = new SearchMapping();

    public static ProductMapping getInstance(){
        return new ProductMapping();
    }
}

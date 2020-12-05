package com.zelinskiyrk.store.basket.mapping;

import com.zelinskiyrk.store.base.api.response.SearchResponse;
import com.zelinskiyrk.store.base.mapping.BaseMapping;
import com.zelinskiyrk.store.basket.api.request.BasketRequest;
import com.zelinskiyrk.store.basket.api.response.BasketResponse;
import com.zelinskiyrk.store.basket.model.BasketDoc;
import com.zelinskiyrk.store.product.repository.ProductRepository;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BasketMapping {

    public BasketMapping() {
    }

    public static class RequestMapping extends BaseMapping<BasketRequest, BasketDoc> {

        @Override
        public BasketDoc convert(BasketRequest basketRequest){
            return BasketDoc.builder()
                        .id(basketRequest.getId())
                        .productId(basketRequest.getProductId())
                        .productQuantity(basketRequest.getProductQuantity())
                        .orderPrice(basketRequest.getOrderPrice())
                        .deliveryPrice(basketRequest.getDeliveryPrice())
                        .totalPrice(basketRequest.getTotalPrice())
                    .build();
        }

        @Override
        public BasketRequest unmapping(BasketDoc basketDoc) {
            throw new RuntimeException("don't use this");
        }
    }

    public static class ResponseMapping extends BaseMapping<BasketDoc, BasketResponse> {


        @Override
        public BasketResponse convert(BasketDoc basketDoc){
            return BasketResponse.builder()
                        .id(basketDoc.getId().toString())
                        .product(basketDoc.getProduct())
                        .productQuantity(basketDoc.getProductQuantity().toString())
                        .orderPrice(basketDoc.getOrderPrice().toString())
                        .deliveryPrice(basketDoc.getDeliveryPrice().toString())
                        .totalPrice(basketDoc.getTotalPrice().toString())
                        .sessionId(basketDoc.getSessionId())
                    .build();
        }

        @Override
        public BasketDoc unmapping(BasketResponse basketResponse) {
            throw new RuntimeException("don't use this");
        }
    }

    public static class SearchMapping extends BaseMapping<List<BasketDoc>, List<BasketResponse>>{
        private ResponseMapping responseMapping = new ResponseMapping();

        @Override
        public List<BasketResponse> convert(List<BasketDoc> basketDocs) {
            return basketDocs.stream().map(responseMapping::convert).collect(Collectors.toList());
        }

        @Override
        public List<BasketDoc> unmapping(List<BasketResponse> basketResponses) {
            throw new RuntimeException("don't use this");
        }
    }

    private final RequestMapping request = new RequestMapping();
    private final ResponseMapping response = new ResponseMapping();
    private final SearchMapping search = new SearchMapping();

    public static BasketMapping getInstance(){
        return new BasketMapping();
    }
}

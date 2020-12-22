package com.zelinskiyrk.store.order.mapping;

import com.zelinskiyrk.store.base.api.response.SearchResponse;
import com.zelinskiyrk.store.base.mapping.BaseMapping;
import com.zelinskiyrk.store.order.api.response.OrderDocResponse;
import com.zelinskiyrk.store.order.api.response.OrderResponse;
import com.zelinskiyrk.store.order.model.OrderDoc;
import lombok.Getter;

import java.util.stream.Collectors;

@Getter
public class OrderMapping {

//    public static class RequestMapping extends BaseMapping<OrderRequest, OrderDoc> {
//
//        @Override
//        public OrderDoc convert(OrderRequest orderRequest) {
//            return OrderDoc.builder()
////                    .id(orderRequest.getId())
////                    .guestDoc(orderRequest.getGuestRequest())
////                    .deliveryTime(orderRequest.getDeliveryTime())
////                    .paymentMethod(orderRequest.getPaymentMethod())
//                    .build();
//        }
//
//        @Override
//        public OrderRequest unmapping(OrderDoc orderDoc) {
//            throw new RuntimeException("don't use this");
//        }
//    }

    public static class ResponseMapping extends BaseMapping<OrderDoc, OrderResponse> {

        @Override
        public OrderResponse convert(OrderDoc orderDoc) {
            return OrderResponse.builder()
                    .orderCount(orderDoc.getOrderNumber())
                    .build();
        }

        @Override
        public OrderDoc unmapping(OrderResponse orderResponse) {
            throw new RuntimeException("don't use this");
        }
    }

    public static class ResponseDocMapping extends BaseMapping<OrderDoc, OrderDocResponse> {

        @Override
        public OrderDocResponse convert(OrderDoc orderDoc) {
            return OrderDocResponse.builder()
                    .orderNumber(orderDoc.getOrderNumber())
                    .guestId(orderDoc.getGuestId())
                    .productList(orderDoc.getProductList())
                    .orderPrice(orderDoc.getOrderPrice())
                    .deliveryTime(orderDoc.getDeliveryTime())
                    .paymentMethod(orderDoc.getPaymentMethod())
                    .build();
        }

        @Override
        public OrderDoc unmapping(OrderDocResponse orderDocResponse) {
            throw new RuntimeException("don't use this");
        }
    }

    public static class SearchMapping extends BaseMapping<SearchResponse<OrderDoc>, SearchResponse<OrderResponse>> {
        private final ResponseMapping responseMapping = new ResponseMapping();

        @Override
        public SearchResponse<OrderResponse> convert(SearchResponse<OrderDoc> searchResponse) {
            return SearchResponse.of(
                    searchResponse.getList().stream().map(responseMapping::convert).collect(Collectors.toList()),
                    searchResponse.getCount()
            );
        }

        @Override
        public SearchResponse<OrderDoc> unmapping(SearchResponse<OrderResponse> orderResponses) {
            throw new RuntimeException("don't use this");
        }
    }

    //    private final RequestMapping request = new RequestMapping();
    private final ResponseMapping response = new ResponseMapping();
    private final ResponseDocMapping responseDocMapping = new ResponseDocMapping();
    private final SearchMapping search = new SearchMapping();

    public static OrderMapping getInstance() {
        return new OrderMapping();
    }
}

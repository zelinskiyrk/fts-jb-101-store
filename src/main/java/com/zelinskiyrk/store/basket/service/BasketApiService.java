package com.zelinskiyrk.store.basket.service;

import com.zelinskiyrk.store.basket.api.request.BasketRequest;
import com.zelinskiyrk.store.basket.api.response.BasketResponse;
import com.zelinskiyrk.store.basket.exception.ProductBasketExistException;
import com.zelinskiyrk.store.basket.model.BasketDoc;
import com.zelinskiyrk.store.basket.model.ProductBasketDoc;
import com.zelinskiyrk.store.basket.repository.BasketRepository;
import com.zelinskiyrk.store.city.model.CityDoc;
import com.zelinskiyrk.store.city.repository.CityRepository;
import com.zelinskiyrk.store.product.model.ProductDoc;
import com.zelinskiyrk.store.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.session.data.mongo.MongoSession;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class BasketApiService {
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final CityRepository cityRepository;
    private final MongoTemplate mongoTemplate;

//    @Autowired
//    MongoTemplate mongoTemplate;


    public BasketDoc getBasket(HttpServletRequest servletRequest) {

        String sessionId = servletRequest.getSession(true).getId();
        Optional<BasketDoc> optionalBasket = basketRepository.findBySessionId(sessionId);
        BasketDoc basketDoc;
        if (optionalBasket.isEmpty()) {
            basketDoc = new BasketDoc();
            basketDoc.setSessionId(sessionId);
        } else {
            basketDoc = optionalBasket.get();
        }
        return basketDoc;
    }


    public BasketResponse getBasketResponse(BasketDoc basketDoc, ObjectId cityId) {
        List<BasketResponse.Product> responseProducts = getResponseProducts(basketDoc, cityId);

        Double price = 0.0;
        for (BasketResponse.Product product : responseProducts
        ) {
            price += product.getPrice();
        }

        CityDoc cityDoc = cityRepository.findCityDocById(cityId);
        Double deliveryPrice = cityDoc.getDeliveryPrice();

        return BasketResponse.builder()
                .products(responseProducts)
                .deliveryPrice(deliveryPrice)
                .orderPrice(price)
                .totalPrice(price + deliveryPrice)
                .build();
    }


    public List<BasketResponse.Product> getResponseProducts(BasketDoc basketDoc, ObjectId cityId) {
        List<ProductDoc> productDocs = getProductDocList(basketDoc);
        return IntStream.range(0, productDocs.size())
                .mapToObj((i) -> {
                    ProductDoc product = productDocs.get(i);
                    ProductBasketDoc basketProduct = basketDoc.getProducts().get(i);

                    Double productPrice = product.getPriceByCityId(cityId.toString());
                    Integer quantity = basketProduct.getQuantity();

                    return BasketResponse.Product.builder()
                            .productId(product.getId().toString())
                            .productName(product.getProductName())
                            .photoId(product.getPhotoId().toString())
                            .quantity(quantity)
                            .price(productPrice * quantity)
                            .build();
                }).collect(Collectors.toList());
    }

    public List<ProductDoc> getProductDocList(BasketDoc basketDoc) {
        List<String> productIds = basketDoc.getProducts()
                .stream()
                .map(ProductBasketDoc::getProductId)
                .collect(Collectors.toList());

        List<ProductDoc> productDocs = new ArrayList<>();

        for (String productId : productIds
        ) {
            ProductDoc productDoc = productRepository.findById(productId);
            productDocs.add(productDoc);
        }
        return productDocs;
    }


    public BasketResponse addProduct(BasketRequest request, HttpServletRequest servletRequest) throws ProductBasketExistException {

        BasketDoc basketDoc = getBasket(servletRequest);
        List<ProductBasketDoc> basketProducts = basketDoc.getProducts();

        for (ProductBasketDoc product : basketProducts
        ) {
            if (product.getProductId().equals(request.getProductId())) {
                throw new ProductBasketExistException();
            }
        }

        ProductBasketDoc productBasketDoc = new ProductBasketDoc(
                request.getProductId(), 1
        );

        basketDoc.getProducts().add(productBasketDoc);
        basketRepository.save(basketDoc);
        return getBasketResponse(basketDoc, request.getCityId());
    }

    public BasketResponse deleteProduct(BasketRequest request, HttpServletRequest servletRequest) {
        BasketDoc basketDoc = getBasket(servletRequest);
        List<ProductBasketDoc> basketProducts = basketDoc.getProducts();

        basketProducts = basketProducts
                .stream()
                .filter(
                        b -> !b.getProductId().equals(request.getProductId())
                )
                .collect(Collectors.toList());

        basketDoc.setProducts(basketProducts);
        basketRepository.save(basketDoc);
        return getBasketResponse(basketDoc, request.getCityId());
    }

    public BasketResponse increaseProduct(BasketRequest request, HttpServletRequest servletRequest) {
        BasketDoc basketDoc = getBasket(servletRequest);
        List<ProductBasketDoc> basketProducts = basketDoc.getProducts();

        for (ProductBasketDoc product : basketProducts
        ) {
            if (product.getProductId().equals(request.getProductId())) {
                int productCount = product.getQuantity();
                productCount = productCount + 1;
                product.setQuantity(productCount);
            }
        }
        basketDoc.setProducts(basketProducts);
        basketRepository.save(basketDoc);
        return getBasketResponse(basketDoc, request.getCityId());
    }

    public BasketResponse decreaseProduct(BasketRequest request, HttpServletRequest servletRequest) {
        BasketDoc basketDoc = getBasket(servletRequest);
        List<ProductBasketDoc> basketProducts = basketDoc.getProducts();

        for (ProductBasketDoc product : basketProducts
        ) {
            if (product.getProductId().equals(request.getProductId())) {
                int productCount = product.getQuantity();
                if (productCount > 1) {
                    productCount = productCount - 1;
                    product.setQuantity(productCount);
                } else {
                    basketProducts.remove(product);
                    break;
                }
            }
        }
        basketDoc.setProducts(basketProducts);
        basketRepository.save(basketDoc);
        return getBasketResponse(basketDoc, request.getCityId());
    }


    public void deleteOldBaskets() {
        List<String> inactiveSessionList = getInactiveSessionList();
        basketRepository.deleteBySessionIdIn(inactiveSessionList);
    }

    private List<String> getInactiveSessionList() {
        List<String> activeSessions = getActiveSessionList();
        List<String> basketSessions = getBasketSessionIds();
        basketSessions.removeAll(activeSessions);

        return basketSessions;
    }

    private List<String> getActiveSessionList() {
        List<MongoSession> sessions = mongoTemplate.findAll(MongoSession.class, "sessions");
        List<String> activeSessions = new ArrayList<>();
        for (MongoSession session : sessions
        ) {
            String id = session.getId();
            activeSessions.add(id);
        }
        return activeSessions;
    }

    private List<String> getBasketSessionIds() {
        List<BasketDoc> allBaskets = basketRepository.findAll();
        List<String> basketSession = new ArrayList<>();
        for (BasketDoc basket : allBaskets
        ) {
            basketSession.add(basket.getSessionId());
        }
        return basketSession;
    }
}

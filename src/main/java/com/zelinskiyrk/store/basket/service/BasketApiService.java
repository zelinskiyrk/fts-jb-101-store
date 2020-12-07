package com.zelinskiyrk.store.basket.service;

import com.zelinskiyrk.store.basket.api.request.BasketRequest;
import com.zelinskiyrk.store.basket.exception.BasketExistException;
import com.zelinskiyrk.store.basket.exception.BasketNotExistException;
import com.zelinskiyrk.store.basket.mapping.BasketMapping;
import com.zelinskiyrk.store.basket.model.BasketDoc;
import com.zelinskiyrk.store.basket.model.ProductBasketDoc;
import com.zelinskiyrk.store.basket.repository.BasketRepository;
import com.zelinskiyrk.store.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketApiService {
    private final BasketRepository basketRepository;
    private final MongoTemplate mongoTemplate;
    private final ProductRepository productRepository;

    public BasketDoc addBasket(BasketRequest request, HttpServletRequest servletRequest) {

//        servletRequest.getSession(true).invalidate();

        String sessionId = servletRequest.getSession(true).getId();
        Optional<BasketDoc> optionalBasket = basketRepository.findBySessionId(sessionId);
        BasketDoc basketDoc;
        if (optionalBasket.isEmpty()) {
            basketDoc = new BasketDoc();
            basketDoc.setSessionId(sessionId);
        } else {
            basketDoc = optionalBasket.get();
        }

        ProductBasketDoc productBasketDoc = new ProductBasketDoc(
                request.getProductId(), 1
        );
        basketDoc.getProducts().add(productBasketDoc);
        basketRepository.save(basketDoc);

        return null;
    }

    public Optional<BasketDoc> findById(ObjectId id){
        return basketRepository.findById(id);
    }

    public List<BasketDoc> search(
//            SearchRequest request
    ){
//        Query query = new Query();
//        if (request. != null) {
//            query.addCriteria(Criteria.where("cityId").is(request.getCityId()));
//        }
//        Long count = mongoTemplate.count(query, BasketDoc.class);
//
//        List<BasketDoc> basketDocs = mongoTemplate.find(query, BasketDoc.class);
        return basketRepository.findAll();
    }

//    public BasketDoc update(BasketRequest request) throws BasketNotExistException {
//        Optional<BasketDoc> basketDocOptional = basketRepository.findById(request.getId());
//        if (basketDocOptional.isPresent() == false){
//            throw new BasketNotExistException();
//        }
//
//        BasketDoc oldDoc = basketDocOptional.get();
//
//        BasketDoc basketDoc = BasketMapping.getInstance().getRequest().convert(request);
//        basketDoc.setId(request.getId());
//        basketRepository.save(basketDoc);
//
//        return basketDoc;
//    }

    public void delete(ObjectId id){
        basketRepository.deleteById(id);
    }
}
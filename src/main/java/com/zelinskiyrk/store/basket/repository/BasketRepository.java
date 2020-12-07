package com.zelinskiyrk.store.basket.repository;

import com.zelinskiyrk.store.basket.model.BasketDoc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface BasketRepository extends MongoRepository<BasketDoc, ObjectId> {
//    public Optional<BasketDoc> findAllByBasketName(String basketName);
    public Optional<BasketDoc> findBySessionId(String sessionId);
}

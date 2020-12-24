package com.zelinskiyrk.store.basket.repository;

import com.zelinskiyrk.store.basket.model.BasketDoc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepository extends MongoRepository<BasketDoc, ObjectId> {
    Optional<BasketDoc> findBySessionId(String sessionId);

    void deleteBySessionIdIn(List<String> ids);
}

package com.zelinskiyrk.store.order.repository;

import com.zelinskiyrk.store.order.model.OrderDoc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<OrderDoc, ObjectId> {
    public OrderDoc findByOrderNumber(Integer orderNumber);
}

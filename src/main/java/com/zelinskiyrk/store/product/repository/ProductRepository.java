package com.zelinskiyrk.store.product.repository;

import com.zelinskiyrk.store.product.model.ProductDoc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<ProductDoc, ObjectId> {
    Optional<ProductDoc> findAllByProductName(String productName);

    ProductDoc findById(String productId);
}

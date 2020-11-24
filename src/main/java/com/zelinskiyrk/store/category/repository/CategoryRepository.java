package com.zelinskiyrk.store.category.repository;

import com.zelinskiyrk.store.category.model.CategoryDoc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<CategoryDoc, ObjectId> {
    public Optional<CategoryDoc> findByCategoryName(String categoryName);
}

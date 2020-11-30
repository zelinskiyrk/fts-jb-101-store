package com.zelinskiyrk.store.street.repository;

import com.zelinskiyrk.store.street.model.StreetDoc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StreetRepository extends MongoRepository<StreetDoc, ObjectId> {
    public Optional<StreetDoc> findByCityIdAndStreetName(ObjectId cityId, String streetName);
}

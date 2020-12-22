package com.zelinskiyrk.store.city.repository;

import com.zelinskiyrk.store.city.model.CityDoc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends MongoRepository<CityDoc, ObjectId> {
    Optional<CityDoc> findCityDocByCityName(String city);

    CityDoc findCityDocById(ObjectId id);

}

package com.zelinskiyrk.store.photo.repository;

import com.zelinskiyrk.store.photo.model.PhotoDoc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoRepository extends MongoRepository<PhotoDoc, ObjectId> {
    public Optional<PhotoDoc> findAllByPhotoName(String photoName);
}

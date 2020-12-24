package com.zelinskiyrk.store.guest.repository;

import com.zelinskiyrk.store.guest.model.GuestDoc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends MongoRepository<GuestDoc, ObjectId> {
    Optional<GuestDoc> findAllByGuestPhoneNumber(String guestPhoneNumber);
    GuestDoc findByGuestPhoneNumber(String guestPhoneNumber);
}

package com.zelinskiyrk.store.guest.service;

import com.zelinskiyrk.store.guest.api.request.GuestRequest;
import com.zelinskiyrk.store.guest.exception.GuestExistException;
import com.zelinskiyrk.store.guest.exception.GuestNotExistException;
import com.zelinskiyrk.store.guest.mapping.GuestMapping;
import com.zelinskiyrk.store.guest.model.GuestDoc;
import com.zelinskiyrk.store.guest.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestApiService {
    private final GuestRepository guestRepository;
    private final MongoTemplate mongoTemplate;

    public GuestDoc addGuest(GuestRequest request)
            throws GuestExistException {

        if (guestRepository.findAllByGuestPhoneNumber(request.getGuestPhoneNumber()).isPresent()) {
            throw new GuestExistException();
        }

        GuestDoc guestDoc = GuestMapping.getInstance().getRequest().convert(request);
        guestRepository.save(guestDoc);
        return guestDoc;
    }

    public Optional<GuestDoc> findById(ObjectId id) {
        return guestRepository.findById(id);
    }

    //TODO Нужен поиск по номеру телефона, фамилии
    public List<GuestDoc> search() {
//    public List<GuestDoc> search(
//            SearchRequest request
//    ){
//        Query query = new Query();
//        if (request.getQuery() != null) {
//            query.addCriteria(Criteria.where("cityId").is(request.getQuery()));
//        }
//        Long count = mongoTemplate.count(query, GuestDoc.class);
//
//        List<GuestDoc> guestDocs = mongoTemplate.find(query, GuestDoc.class);
//        return SearchResponse.of(guestDocs, count);
        return guestRepository.findAll();
    }

    public GuestDoc update(GuestRequest request) throws GuestNotExistException {
        Optional<GuestDoc> guestDocOptional = guestRepository.findById(request.getId());
        if (guestDocOptional.isEmpty()) {
            throw new GuestNotExistException();
        }

        GuestDoc guestDoc = GuestMapping.getInstance().getRequest().convert(request);
        guestDoc.setId(request.getId());
        guestRepository.save(guestDoc);

        return guestDoc;
    }

    public void delete(ObjectId id) {
        guestRepository.deleteById(id);
    }
}

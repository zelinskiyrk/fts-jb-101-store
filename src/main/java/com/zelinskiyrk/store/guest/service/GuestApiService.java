package com.zelinskiyrk.store.guest.service;

import com.zelinskiyrk.store.guest.api.request.GuestRequest;
import com.zelinskiyrk.store.guest.exception.GuestNotExistException;
import com.zelinskiyrk.store.guest.mapping.GuestMapping;
import com.zelinskiyrk.store.guest.model.GuestDoc;
import com.zelinskiyrk.store.guest.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestApiService {
    private final GuestRepository guestRepository;

    public GuestDoc addGuest(GuestRequest request) {

        if (guestRepository.findAllByGuestPhoneNumber(request.getGuestPhoneNumber()).isPresent()) {
            return guestRepository.findByGuestPhoneNumber(request.getGuestPhoneNumber());
        }

        GuestDoc guestDoc = GuestMapping.getInstance().getRequest().convert(request);
        guestRepository.save(guestDoc);
        return guestDoc;
    }

    public Optional<GuestDoc> findById(ObjectId id) {
        return guestRepository.findById(id);
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

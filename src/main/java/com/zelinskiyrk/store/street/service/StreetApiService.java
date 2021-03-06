package com.zelinskiyrk.store.street.service;

import com.zelinskiyrk.store.city.exception.CityNotExistException;
import com.zelinskiyrk.store.city.repository.CityRepository;
import com.zelinskiyrk.store.street.api.request.StreetRequest;
import com.zelinskiyrk.store.street.exception.StreetExistException;
import com.zelinskiyrk.store.street.exception.StreetNotExistException;
import com.zelinskiyrk.store.street.mapping.StreetMapping;
import com.zelinskiyrk.store.street.model.StreetDoc;
import com.zelinskiyrk.store.street.repository.StreetRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreetApiService {
    private final StreetRepository streetRepository;
    private final MongoTemplate mongoTemplate;
    private final CityRepository cityRepository;

    public StreetDoc addStreet(StreetRequest request)
            throws StreetExistException, CityNotExistException {

        if (streetRepository.findByCityIdAndStreetName(request.getCityId(), request.getStreetName()).isPresent()) {
            throw new StreetExistException();
        }

        if (cityRepository.findById(request.getCityId()).isEmpty()) {
            throw new CityNotExistException();
        }

        StreetDoc streetDoc = StreetMapping.getInstance().getRequest().convert(request);
        streetRepository.save(streetDoc);
        return streetDoc;
    }

    public Optional<StreetDoc> findById(ObjectId id) {
        return streetRepository.findById(id);
    }

    public List<String> search(StreetRequest request) {
        List<StreetDoc> streetDocList = streetRepository.findByCityIdAndStreetNameContainingIgnoreCase(request.getCityId(), request.getStreetName());
        List<String> streetNameList = new ArrayList<>();
        for (StreetDoc streetDoc : streetDocList
        ) {
            streetNameList.add(streetDoc.getStreetName());
        }
        return streetNameList;
    }

    public StreetDoc update(StreetRequest request) throws StreetNotExistException {
        Optional<StreetDoc> streetDocOptional = streetRepository.findById(request.getId());
        if (streetDocOptional.isEmpty()) {
            throw new StreetNotExistException();
        }

        StreetDoc oldDoc = streetDocOptional.get();

        StreetDoc streetDoc = StreetMapping.getInstance().getRequest().convert(request);
        streetDoc.setId(request.getId());
        streetDoc.setCityId(oldDoc.getCityId());
        streetRepository.save(streetDoc);

        return streetDoc;
    }

    public void delete(ObjectId id) {
        streetRepository.deleteById(id);
    }
}

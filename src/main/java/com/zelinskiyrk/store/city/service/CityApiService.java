package com.zelinskiyrk.store.city.service;

import com.zelinskiyrk.store.city.api.request.AddCityRequest;
import com.zelinskiyrk.store.city.api.request.CityRequest;
import com.zelinskiyrk.store.city.exception.CityExistException;
import com.zelinskiyrk.store.city.exception.CityNotExistException;
import com.zelinskiyrk.store.city.model.CityDoc;
import com.zelinskiyrk.store.city.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityApiService {
    private final CityRepository cityRepository;

    public CityDoc addCity(AddCityRequest request) throws CityExistException {
        if (cityRepository.findCityDocByCityName(request.getCityName()).isPresent()) {
            throw new CityExistException();
        }
        CityDoc cityDoc = new CityDoc();
        cityDoc.setCityName(request.getCityName());
        cityDoc.setDeliveryPrice(request.getDeliveryPrice());
        cityDoc.setDeliveryTime(request.getDeliveryTime());
        cityDoc = cityRepository.save(cityDoc);
        return cityDoc;
    }

    public Optional<CityDoc> findById(ObjectId id) {
        return cityRepository.findById(id);
    }

    public List<CityDoc> search() {
        return cityRepository.findAll();
    }

    public CityDoc update(CityRequest request) throws CityNotExistException {
        Optional<CityDoc> cityDocOptional = cityRepository.findById(request.getId());
        if (cityDocOptional.isEmpty()) {
            throw new CityNotExistException();
        }

        CityDoc cityDoc = cityDocOptional.get();
        cityDoc.setCityName(request.getCityName());
        cityRepository.save(cityDoc);
        return cityDoc;
    }

    public void delete(ObjectId id) {
        cityRepository.deleteById(id);
    }
}

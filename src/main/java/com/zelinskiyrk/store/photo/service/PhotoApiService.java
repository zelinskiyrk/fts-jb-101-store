package com.zelinskiyrk.store.photo.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.zelinskiyrk.store.base.api.response.SearchResponse;
import com.zelinskiyrk.store.photo.api.request.PhotoRequest;
import com.zelinskiyrk.store.photo.api.request.PhotoSearchRequest;
import com.zelinskiyrk.store.photo.exception.PhotoExistException;
import com.zelinskiyrk.store.photo.exception.PhotoNotExistException;
import com.zelinskiyrk.store.photo.mapping.PhotoMapping;
import com.zelinskiyrk.store.photo.model.PhotoDoc;
import com.zelinskiyrk.store.photo.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoApiService {
    private final PhotoRepository photoRepository;
    private final MongoTemplate mongoTemplate;
    private final GridFsTemplate gridFsTemplate;
    private final GridFsOperations operations;

    public PhotoDoc addPhoto(MultipartFile file)
            throws PhotoExistException, IOException {
        if (photoRepository.findAllByPhotoName(file.getOriginalFilename()).isPresent()) {
            throw new PhotoExistException();
        }

        DBObject metaData = new BasicDBObject();
        metaData.put("type", file.getContentType());
        metaData.put("title", file.getOriginalFilename());

        ObjectId id = gridFsTemplate.store(
                file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metaData
        );

        PhotoDoc photoDoc = PhotoDoc.builder()
                .id(id)
                .photoName(file.getOriginalFilename())
                .contentType(file.getContentType())
                .build();

        photoRepository.save(photoDoc);
        return photoDoc;
    }

    public InputStream downloadById(ObjectId id) throws ChangeSetPersister.NotFoundException, IOException {
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        if (file == null) throw new ChangeSetPersister.NotFoundException();
        return operations.getResource(file).getInputStream();
    }

    public Optional<PhotoDoc> findById(ObjectId id) {
        return photoRepository.findById(id);
    }

    public SearchResponse<PhotoDoc> search(
            PhotoSearchRequest request
    ) {
        Query query = new Query();
        if (request.getPhotoName() != null) {
            query.addCriteria(Criteria.where("photoName").is(request.getPhotoName()));
        }
        Long count = mongoTemplate.count(query, PhotoDoc.class);

        List<PhotoDoc> photoDocs = mongoTemplate.find(query, PhotoDoc.class);
        return SearchResponse.of(photoDocs, count);
    }

    public PhotoDoc update(PhotoRequest request) throws PhotoNotExistException {
        Optional<PhotoDoc> photoDocOptional = photoRepository.findById(request.getId());
        if (photoDocOptional.isEmpty()) {
            throw new PhotoNotExistException();
        }

        PhotoDoc photoDoc = PhotoMapping.getInstance().getRequest().convert(request);
        photoDoc.setId(request.getId());
        photoRepository.save(photoDoc);

        return photoDoc;
    }

    public void delete(ObjectId id) {
        photoRepository.deleteById(id);
    }
}

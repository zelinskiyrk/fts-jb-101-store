package com.zelinskiyrk.store.category.service;

import com.zelinskiyrk.store.city.exception.CityNotExistException;
import com.zelinskiyrk.store.city.repository.CityRepository;
import com.zelinskiyrk.store.category.api.request.CategoryRequest;
import com.zelinskiyrk.store.category.exception.CategoryExistException;
import com.zelinskiyrk.store.category.exception.CategoryNotExistException;
import com.zelinskiyrk.store.category.mapping.CategoryMapping;
import com.zelinskiyrk.store.category.model.CategoryDoc;
import com.zelinskiyrk.store.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryApiService {
    private final CategoryRepository categoryRepository;
    private final MongoTemplate mongoTemplate;

    public CategoryDoc addCategory(CategoryRequest request)
            throws CategoryExistException {
        if (categoryRepository.findByCategoryName(request.getCategoryName()).isPresent() == true){
            throw new CategoryExistException();
        }

        CategoryDoc categoryDoc = CategoryMapping.getInstance().getRequest().convert(request);
        categoryRepository.save(categoryDoc);
        return categoryDoc;
    }

    public Optional<CategoryDoc> findById(ObjectId id){
        return categoryRepository.findById(id);
    }

    public List<CategoryDoc> search(){
        return categoryRepository.findAll();
    }

    public CategoryDoc update(CategoryRequest request) throws CategoryNotExistException {
        Optional<CategoryDoc> categoryDocOptional = categoryRepository.findById(request.getId());
        if (categoryDocOptional.isPresent() == false){
            throw new CategoryNotExistException();
        }


        CategoryDoc categoryDoc = CategoryMapping.getInstance().getRequest().convert(request);
        categoryDoc.setId(request.getId());
        categoryRepository.save(categoryDoc);

        return categoryDoc;
    }

    public void delete(ObjectId id){
        categoryRepository.deleteById(id);
    }
}
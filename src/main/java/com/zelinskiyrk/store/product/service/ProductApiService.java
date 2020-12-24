package com.zelinskiyrk.store.product.service;

import com.zelinskiyrk.store.base.api.response.SearchResponse;
import com.zelinskiyrk.store.product.api.request.ProductRequest;
import com.zelinskiyrk.store.product.api.request.ProductSearchRequest;
import com.zelinskiyrk.store.product.exception.ProductExistException;
import com.zelinskiyrk.store.product.exception.ProductNotExistException;
import com.zelinskiyrk.store.product.mapping.ProductMapping;
import com.zelinskiyrk.store.product.model.ProductDoc;
import com.zelinskiyrk.store.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductApiService {
    private final ProductRepository productRepository;
    private final MongoTemplate mongoTemplate;

    public ProductDoc addProduct(ProductRequest request)
            throws ProductExistException {

        if (productRepository.findAllByProductName(request.getProductName()).isPresent()) {
            throw new ProductExistException();
        }

        ProductDoc productDoc = ProductMapping.getInstance().getRequest().convert(request);
        productRepository.save(productDoc);
        return productDoc;
    }

    public Optional<ProductDoc> findById(ObjectId id) {
        return productRepository.findById(id);
    }


    public SearchResponse<ProductDoc> search(
            ProductSearchRequest request
    ) {
        Query query = new Query();
        if (request.getProductName() != null) {
            query.addCriteria(Criteria.where("productName").is(request.getProductName()));
        }
        Long count = mongoTemplate.count(query, ProductDoc.class);

        List<ProductDoc> productDocs = mongoTemplate.find(query, ProductDoc.class);
        return SearchResponse.of(productDocs, count);
    }


    public ProductDoc update(ProductRequest request) throws ProductNotExistException {
        Optional<ProductDoc> productDocOptional = productRepository.findById(request.getId());
        if (productDocOptional.isEmpty()) {
            throw new ProductNotExistException();
        }


        ProductDoc productDoc = ProductMapping.getInstance().getRequest().convert(request);
        productDoc.setId(request.getId());
        productRepository.save(productDoc);

        return productDoc;
    }

    public void delete(ObjectId id) {
        productRepository.deleteById(id);
    }
}

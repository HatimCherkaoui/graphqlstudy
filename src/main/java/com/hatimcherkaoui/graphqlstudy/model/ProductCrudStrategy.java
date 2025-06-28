package com.hatimcherkaoui.graphqlstudy.model;


import com.hatimcherkaoui.graphqlstudy.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductCrudStrategy {
    Product createProduct(Product input);

    Product updateProduct(Product input);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    Boolean deleteById(Long id);
}
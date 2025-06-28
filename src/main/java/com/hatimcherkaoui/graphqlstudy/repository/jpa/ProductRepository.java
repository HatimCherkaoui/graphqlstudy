package com.hatimcherkaoui.graphqlstudy.repository.jpa;

import com.hatimcherkaoui.graphqlstudy.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
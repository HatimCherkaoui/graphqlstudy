package com.hatimcherkaoui.graphqlstudy.model;

import com.hatimcherkaoui.graphqlstudy.model.dto.CreateProductInput;
import com.hatimcherkaoui.graphqlstudy.model.dto.ShowProductOutput;
import com.hatimcherkaoui.graphqlstudy.model.dto.UpdateProductInput;
import com.hatimcherkaoui.graphqlstudy.model.entity.Product;

import java.time.LocalDateTime;

public class Mapper {

    public static ShowProductOutput toShowProductOutput(Product product) {
        return new ShowProductOutput(product.getId(), product.getTitle(), product.getDescription(), product.getPrice(), product.getCreatedAt(), product.getUpdatedAt());
    }

    public static Product toProduct(CreateProductInput input) {
        var product = new Product();
        product.setPrice(input.price());
        product.setTitle(input.title());
        product.setDescription(input.description());
        product.setCreatedAt(LocalDateTime.now());
        return product;
    }

    public static Product toProduct(UpdateProductInput input) {
        var product = new Product();
        product.setId(input.id());
        product.setPrice(input.price());
        product.setTitle(input.title());
        product.setDescription(input.description());
        product.setUpdatedAt(LocalDateTime.now());
        return product;
    }
}

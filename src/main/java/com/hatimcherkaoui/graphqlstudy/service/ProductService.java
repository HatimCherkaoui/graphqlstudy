package com.hatimcherkaoui.graphqlstudy.service;

import com.hatimcherkaoui.graphqlstudy.model.Mapper;
import com.hatimcherkaoui.graphqlstudy.model.ProductCrudStrategy;
import com.hatimcherkaoui.graphqlstudy.model.dto.CreateProductInput;
import com.hatimcherkaoui.graphqlstudy.model.dto.ShowProductOutput;
import com.hatimcherkaoui.graphqlstudy.model.dto.UpdateProductInput;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hatimcherkaoui.graphqlstudy.model.Mapper.toProduct;
import static com.hatimcherkaoui.graphqlstudy.model.Mapper.toShowProductOutput;

@Service
public class ProductService {

    private final ProductCrudStrategy productCrud;

    public ProductService(ProductCrudStrategy productCrud) {
        this.productCrud = productCrud;
    }


    public ShowProductOutput create(CreateProductInput input) {
        return toShowProductOutput(productCrud.createProduct(toProduct(input)));
    }

    public ShowProductOutput update(UpdateProductInput input) {

        return toShowProductOutput(productCrud.updateProduct(toProduct(input)));
    }

    public List<ShowProductOutput> findAll() {
        return productCrud.findAll().stream().map(Mapper::toShowProductOutput).toList();
    }

    public ShowProductOutput findById(Long id) {
        return productCrud.findById(id)
                .map(Mapper::toShowProductOutput)
                .orElseThrow(() -> new RuntimeException("Product not found."));
    }

    public Boolean delete(Long id) {
        return productCrud.deleteById(id);
    }
}

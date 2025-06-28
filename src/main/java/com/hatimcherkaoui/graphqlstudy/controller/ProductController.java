package com.hatimcherkaoui.graphqlstudy.controller;

import com.hatimcherkaoui.graphqlstudy.model.dto.CreateProductInput;
import com.hatimcherkaoui.graphqlstudy.model.dto.ShowProductOutput;
import com.hatimcherkaoui.graphqlstudy.model.dto.UpdateProductInput;
import com.hatimcherkaoui.graphqlstudy.service.ProductService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }


    @QueryMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<ShowProductOutput> products() {
        return service.findAll();
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ShowProductOutput productById(@Argument Long id) {
        return service.findById(id);
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ShowProductOutput addProduct(@Argument("input") CreateProductInput input) {
        return service.create(input);
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ShowProductOutput updateProduct(@Argument("input") UpdateProductInput input) {
        return service.update(input);
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Boolean deleteProduct(Long id) {
        return service.delete(id);
    }

}
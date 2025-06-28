package com.hatimcherkaoui.graphqlstudy.repository.jpa;

import com.hatimcherkaoui.graphqlstudy.model.ProductCrudStrategy;
import com.hatimcherkaoui.graphqlstudy.model.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductJpaStrategy implements ProductCrudStrategy {
    private final ProductRepository repository;

    public ProductJpaStrategy(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product createProduct(Product input) {
        return repository.save(input);
    }

    @Override
    public Product updateProduct(Product input) {
        var product = repository.findById(input.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setTitle(input.getTitle());
        product.setPrice(input.getPrice());
        product.setDescription(input.getDescription());
        return repository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Boolean deleteById(Long id) {
        repository.deleteById(id);
        return repository.existsById(id);
    }
}

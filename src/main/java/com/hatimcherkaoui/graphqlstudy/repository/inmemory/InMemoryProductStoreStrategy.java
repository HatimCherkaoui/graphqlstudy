package com.hatimcherkaoui.graphqlstudy.repository.inmemory;

import com.hatimcherkaoui.graphqlstudy.model.ProductCrudStrategy;
import com.hatimcherkaoui.graphqlstudy.model.entity.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Profile("in-memory")
public class InMemoryProductStoreStrategy implements ProductCrudStrategy {

    private final Map<Long, Product> store = new HashMap<>();
    private long idCounter = 1;

    @Override
    public Product createProduct(Product input) {
        if (input.getId() == null) {
            input.setId(idCounter++);
        }
        store.put(input.getId(), input);
        return input;
    }

    @Override
    public Product updateProduct(Product input) {
        if (store.containsKey(input.getId())) {
            return input;
        }
        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Product> findAll() {
        return store.values().stream().toList();
    }

    @Override
    public Boolean deleteById(Long id) {
        return store.remove(id) != null;
    }


}
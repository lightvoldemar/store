package com.pillikan.store.repository;

import com.pillikan.store.dto.ProductDto;
import com.pillikan.store.model.Product;

import java.util.List;

public interface ProductRepository {
    Product getById(int id);

    List<Product> getAll();

    List<Product> getByCategory(int categoryId);

    boolean delete(int id);

    Product create(Product product);
}

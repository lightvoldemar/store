package com.pillikan.store.repository;

import com.pillikan.store.model.Category;
import com.pillikan.store.model.Product;

import java.util.List;

public interface CategoryRepository {
    Category getById(int id);

    List<Category> getAll();

    boolean delete(int id);

    Category create(Category category);
}

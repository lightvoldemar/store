package com.pillikan.store.service;

import com.pillikan.store.dto.CategoryDto;
import com.pillikan.store.model.Category;
import com.pillikan.store.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.pillikan.store.util.ValidationUtil.*;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository repository;

    public Category get(int id) {
        Category category = checkNotFoundWithId(repository.getById(id), id);
        log.info("IN get - {} is found with id", id);
        return category;
    }

    public List<Category> getAll() {
        List<Category> categories = repository.getAll();
        log.info("IN getAll - {} categories found", categories.size());
        return categories;
    }

    public void delete(int id) {
        checkNotFound(repository.delete(id), "id = " + id);
        log.info("IN delete - Category is deleted with id {}", id);
    }

    @Transactional
    public Category create(CategoryDto categoryDto) {
        checkNew(categoryDto);
        Category category = new Category();
        copyProps(category, categoryDto);
        Category dbCategory = repository.create(category);
        log.info("IN create - {} is create", dbCategory);
        return dbCategory;
    }

    @Transactional
    public Category update(CategoryDto categoryDto, int id) {
        assureIdConsistent(categoryDto, id);
        Category category = get(id);
        copyProps(category, categoryDto);
        log.info("IN update - {} is updated with {}", category, id);
        return category;
    }

    private void copyProps(Category category, CategoryDto categoryDto) {
        BeanUtils.copyProperties(categoryDto, category);
    }
}

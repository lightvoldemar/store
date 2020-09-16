package com.pillikan.store.controller;

import com.pillikan.store.dto.CategoryDto;
import com.pillikan.store.model.Category;
import com.pillikan.store.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
@Slf4j
public class CategoryController {
    private final CategoryService service;

    @GetMapping
    public List<Category> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Category get(@PathVariable int id) {
        return service.get(id);
    }

    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody CategoryDto category) {
        Category dbCategory = service.create(category);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(dbCategory.getId()).toUri();
        return ResponseEntity.created(location).body(dbCategory);
    }

    @PutMapping("/{id}")
    public Category update(@Valid @RequestBody CategoryDto category, @PathVariable int id) {
        return service.update(category, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public voiдлd delete(@Valid @PathVariable int id) {
        service.delete(id);
    }
}

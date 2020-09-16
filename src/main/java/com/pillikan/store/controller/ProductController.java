package com.pillikan.store.controller;

import com.pillikan.store.dto.ProductDto;
import com.pillikan.store.model.Product;
import com.pillikan.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
@Slf4j
public class ProductController {
    private final ProductService service;

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> create(@Valid @RequestBody ProductDto product) {
        Product dbProduct = service.create(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(dbProduct.getId()).toUri();
        return ResponseEntity.created(location).body(dbProduct);
    }

    @PutMapping("/{id}")
    public Product update(@Valid @RequestBody ProductDto product, @PathVariable int id) {
        return service.update(product, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}

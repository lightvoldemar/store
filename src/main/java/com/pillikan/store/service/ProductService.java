package com.pillikan.store.service;

import com.pillikan.store.dto.ProductDto;
import com.pillikan.store.model.Category;
import com.pillikan.store.model.Product;
import com.pillikan.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.pillikan.store.util.ValidationUtil.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Product getById(int id) {
        Product product = checkNotFoundWithId(productRepository.getById(id), id);
        log.info("IN getById - {} found with id {}", product, id);
        return product;
    }

    public List<Product> getAll() {
        List<Product> products = productRepository.getAll();
        log.info("IN getAll - {} products found", products.size());
        return products;
    }

    public List<Product> getByCategory(int categoryId) {
        List<Product> products = checkNotFound(productRepository.getByCategory(categoryId), "categoryId = " + categoryId);
        log.info("IN getByCategory - {} products found with categoryId {}", products.size(), categoryId);
        return products;
    }

    public void delete(int id) {
        checkNotFound(productRepository.delete(id), "id = " + id);
        log.info("IN delete - Product is deleted with id {}", id);
    }

    @Transactional
    public Product create(ProductDto productDto) {
        checkNew(productDto);
        Product product = new Product();
        copyProps(product, productDto);
        log.info("IN create - {} is created", product);
        return productRepository.create(product);
    }

    @Transactional
    public Product update(ProductDto productDto, int id) {
        assureIdConsistent(productDto, id);
        Product dbProduct = getById(id);
        copyProps(dbProduct, productDto);
        log.info("IN update - {} is updated with id {}", dbProduct, dbProduct.getId());
        return dbProduct;
    }


    private void copyProps(Product product, ProductDto productDto) {
        int categoryId = productDto.getCategoryId();
        if (product.getCategory().getId() != categoryId) {
            Category category = categoryService.get(categoryId);
            product.setCategory(category);
        }
        BeanUtils.copyProperties(productDto, product);
    }
}

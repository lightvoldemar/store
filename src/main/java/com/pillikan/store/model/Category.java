package com.pillikan.store.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    private String title;

    @OneToMany(mappedBy = "category")
    @OrderBy("title")
    @JsonBackReference
    private Set<Product> products = new HashSet<>();

    public Category(String title) {
        this.title = title;
    }

    public void addProduct(Product product) {
        products.add(product);
        product.setCategory(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }
}

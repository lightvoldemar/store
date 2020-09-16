package com.pillikan.store.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(exclude = "category")
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private int price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private Category category;

    public Product(String title, int price, Category category) {
        this.title = title;
        this.price = price;
        this.category = category;
    }
}

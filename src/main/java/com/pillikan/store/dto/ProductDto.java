package com.pillikan.store.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import com.pillikan.store.model.HasId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
public class ProductDto implements HasId {
    private Integer id;
    @NotBlank
    private String title;
    @Positive
    private int price;
    @NotNull
    private Integer categoryId;
}

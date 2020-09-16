package com.pillikan.store.dto;

import com.pillikan.store.model.HasId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class CategoryDto implements HasId {
    private Integer id;
    @NotBlank
    private String title;
}

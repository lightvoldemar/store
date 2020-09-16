package com.pillikan.store.dto;

import com.pillikan.store.model.HasId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class UserDto implements HasId {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Length(min = 5, max = 8)
    private String password;

}

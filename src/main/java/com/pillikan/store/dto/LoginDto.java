package com.pillikan.store.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Length(min = 5, max = 8)
    private String password;
}

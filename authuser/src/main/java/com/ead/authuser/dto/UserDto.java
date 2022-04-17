package com.ead.authuser.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    UUID userId;
    String username;
    String email;
    String password;
    String oldPassword;
    String fullName;
    String phoneNumber;
    String cpf;
    String imageUrl;

}

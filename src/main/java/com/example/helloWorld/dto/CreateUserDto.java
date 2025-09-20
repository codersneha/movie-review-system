package com.example.helloWorld.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserDto {

    final String userName;
    final String email;
    final String firstName;
    final String lastName;

}

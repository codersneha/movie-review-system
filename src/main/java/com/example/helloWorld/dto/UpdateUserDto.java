package com.example.helloWorld.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserDto {

    final String firstName;
    final String lastName;
    final String email;

}
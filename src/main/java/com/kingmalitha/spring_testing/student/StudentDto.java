package com.kingmalitha.spring_testing.student;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
        @NotEmpty(message = "First name cannot be empty")
        String firstname,

        @NotEmpty(message = "Last name cannot be empty")
        String lastname,

        @Email(message = "Email should be valid")
        String email,
        Integer age,
        Integer schoolId
) {
}

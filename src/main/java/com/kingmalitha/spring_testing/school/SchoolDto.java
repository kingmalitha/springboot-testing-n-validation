package com.kingmalitha.spring_testing.school;

import jakarta.validation.constraints.NotEmpty;

public record SchoolDto(
        @NotEmpty
        String name
) {

}

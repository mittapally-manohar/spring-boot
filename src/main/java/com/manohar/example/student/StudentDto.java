package com.manohar.example.student;

import jakarta.validation.constraints.NotEmpty;

public record StudentDto(@NotEmpty String firstName, @NotEmpty String lastName, String email, int schoolId) {
}

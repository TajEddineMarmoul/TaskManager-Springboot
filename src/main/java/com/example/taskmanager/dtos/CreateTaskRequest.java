package com.example.taskmanager.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode

public class CreateTaskRequest {
    @Size(min = 3, max = 255, message = "Title must contain between 3 and 255 characters")
    @NotBlank
    private String title;


}


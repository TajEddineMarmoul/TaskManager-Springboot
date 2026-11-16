package com.example.taskmanager.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode

public class TaskResponse{
    private  Long id;
    private String title;
}

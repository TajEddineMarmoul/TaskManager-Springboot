package com.example.taskmanager.mappers;

import com.example.taskmanager.dtos.CreateTaskRequest;
import com.example.taskmanager.dtos.PatchTaskRequest;
import com.example.taskmanager.dtos.TaskResponse;
import com.example.taskmanager.enteties.Task;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {


    List<TaskResponse> toResponses(List<Task> tasks);


    Task toTask(CreateTaskRequest taskRequest) ;


    TaskResponse toResponse(Task task);

    @BeanMapping(
            nullValuePropertyMappingStrategy  = NullValuePropertyMappingStrategy.IGNORE
    )
    void updateTask(PatchTaskRequest taskRequest , @MappingTarget Task task);
}

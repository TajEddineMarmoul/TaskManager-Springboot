package com.example.taskmanager.controllers;

import com.example.taskmanager.dtos.CreateTaskRequest;
import com.example.taskmanager.dtos.PatchTaskRequest;
import com.example.taskmanager.dtos.TaskResponse;
import com.example.taskmanager.dtos.UpdateTaskRequest;
import com.example.taskmanager.enteties.Task;
import com.example.taskmanager.services.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(
            @Valid @RequestBody CreateTaskRequest request
            ){
        Task task = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> allTasks(){
        List<TaskResponse> tasks = taskService.getAllTasks();
        return  ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(
            @PathVariable Long id

    ){
    TaskResponse taskResponse = taskService.getTaskById(id);
    return ResponseEntity.ok(taskResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long id

    ){
        taskService.deleteTask(id);


        return  ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponse> patchTask(
            @PathVariable Long id,
            @Valid @RequestBody PatchTaskRequest request


    ){
        TaskResponse task = taskService.patchTask(id,request);
        return ResponseEntity.ok(task);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskRequest request


    ){
        TaskResponse task = taskService.updateTask(id,request);
        return ResponseEntity.ok(task);
    }


}

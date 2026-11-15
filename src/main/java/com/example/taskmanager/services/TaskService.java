package com.example.taskmanager.services;

import com.example.taskmanager.Exceptions.TaskNotFoundException;
import com.example.taskmanager.dtos.CreateTaskRequest;
import com.example.taskmanager.dtos.PatchTaskRequest;
import com.example.taskmanager.dtos.TaskResponse;
import com.example.taskmanager.dtos.UpdateTaskRequest;
import com.example.taskmanager.enteties.Task;
import com.example.taskmanager.mappers.TaskMapper;
import com.example.taskmanager.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;



    public List<TaskResponse> getAllTasks(){
        List<Task> tasks = taskRepository.findAll();
        return taskMapper.toResponses(tasks);
    }

    public TaskResponse getTaskById(Long id){
        Task task = taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException(id));
        return  taskMapper.toResponse(task);
    }

    public Task createTask(CreateTaskRequest taskRequest){
        Task task = taskMapper.toTask(taskRequest);
        return  taskRepository.save(task);
    }

    public void deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskRepository.delete(task);


    }

    public TaskResponse patchTask(Long id, PatchTaskRequest request) {

        Task task = taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException(id));

        taskMapper.patchTask(request,task);


        Task newTask = taskRepository.save(task);
        return taskMapper.toResponse(newTask);
    }

    public TaskResponse updateTask(Long id,  UpdateTaskRequest request) {
        Task task = taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException(id));

        taskMapper.updateTask(request,task);


        Task newTask = taskRepository.save(task);
        return taskMapper.toResponse(newTask);

    }
}

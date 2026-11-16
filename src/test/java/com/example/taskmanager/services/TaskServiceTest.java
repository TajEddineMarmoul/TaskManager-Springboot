package com.example.taskmanager.services;

import com.example.taskmanager.Exceptions.TaskNotFoundException;
import com.example.taskmanager.dtos.TaskResponse;
import com.example.taskmanager.enteties.Task;
import com.example.taskmanager.mappers.TaskMapper;
import com.example.taskmanager.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private  TaskRepository taskRepository;

    @Mock
    private  TaskMapper taskMapper;


    @InjectMocks
    private TaskService taskService;

    private Long taskIdDoesNotExist;


    private Long taskId;
    private String title;
    private Task task;
    private TaskResponse expectedResponse;



    @BeforeEach
    void setUp(){

        taskIdDoesNotExist = 999L;

        taskId = 1L;
        title = "Test 1";



        task = new Task();
        task.setId(taskId);
        task.setTitle(title);


        expectedResponse = new TaskResponse();
        expectedResponse.setId(taskId);
        expectedResponse.setTitle(title);

    }

    @Test
    void getTaskById_whenTaskExist_returnTaskResponse() {

        when(taskRepository.findById(taskId))
                .thenReturn(Optional.of(task));

        when(taskMapper.toResponse(task))
                .thenReturn(expectedResponse);

        TaskResponse actualResponse = taskService.getTaskById(taskId);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getTaskById_whenTaskDoesNotExist_returnNotFoundException() {

        when(taskRepository.findById(taskIdDoesNotExist))
                .thenReturn(Optional.empty());



        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.getTaskById(taskIdDoesNotExist)
        );

    }


    @Test
    void patchTask() {
    }

    @Test
    void updateTask() {
    }
}
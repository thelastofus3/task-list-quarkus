package com.thelastofus.service;

import com.thelastofus.dto.task.CreateTaskRequest;
import com.thelastofus.dto.task.TaskResponse;
import com.thelastofus.dto.task.UpdateTaskRequest;

import java.util.List;

public interface TaskService {

    List<TaskResponse> getByUsername(String username);

    TaskResponse create(CreateTaskRequest createTaskRequest, String email);

    TaskResponse delete(Long id, String userEmail);

    TaskResponse update(UpdateTaskRequest updateTaskRequest, String userEmail);

}

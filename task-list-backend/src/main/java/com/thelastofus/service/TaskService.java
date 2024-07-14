package com.thelastofus.service;

import com.thelastofus.dto.task.CreateTaskRequest;
import com.thelastofus.dto.task.UpdateTaskRequest;
import com.thelastofus.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getByUsername(String username);

    Task getById(Long id);

//    Task create(CreateTaskRequest createTaskRequest, JwtEntity jwt);

    Task delete(Long id);

    Task update(UpdateTaskRequest updateTaskRequest);

    boolean isTaskOwner(Long taskId, Long userId);

}

package com.thelastofus.service.impl;

import com.thelastofus.dto.task.UpdateTaskRequest;
import com.thelastofus.model.Task;
import com.thelastofus.service.TaskService;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    @Override
    public List<Task> getByUsername(String username) {
        return List.of();
    }

    @Override
    public Task getById(Long id) {
        return null;
    }

    @Override
    public Task delete(Long id) {
        return null;
    }

    @Override
    public Task update(UpdateTaskRequest updateTaskRequest) {
        return null;
    }

    @Override
    public boolean isTaskOwner(Long taskId, Long userId) {
        return false;
    }
}

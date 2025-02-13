package com.thelastofus.service.impl;

import com.thelastofus.dto.task.CreateTaskRequest;
import com.thelastofus.dto.task.TaskResponse;
import com.thelastofus.dto.task.UpdateTaskRequest;
import com.thelastofus.exception.TaskOwnershipException;
import com.thelastofus.mapper.TaskMapper;
import com.thelastofus.model.Status;
import com.thelastofus.model.Task;
import com.thelastofus.model.User;
import com.thelastofus.repository.TaskRepository;
import com.thelastofus.service.TaskService;
import com.thelastofus.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;
    TaskMapper taskMapper;
    UserService userService;

    @Override
    public List<TaskResponse> getByUsername(String username) {
        List<Task> tasks = taskRepository.findAllByUsername(username);
        return tasks.stream()
                .map(taskMapper::convertToTaskResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TaskResponse create(CreateTaskRequest createTaskRequest, String email) {
        User user = userService.getByEmail(email);
        Task task = taskRepository.save(Task.builder()
                .title(createTaskRequest.getTitle())
                .description(createTaskRequest.getDescription())
                .owner(user)
                .created_at(LocalDateTime.now())
                .status(createTaskRequest.getStatus())
                .build());
        return taskMapper.convertToTaskResponse(task);
    }

    @Override
    @Transactional
    public TaskResponse delete(Long id, String userEmail) {
        User user = userService.getByEmail(userEmail);
        validateTaskAndOwner(id, user.getId());
        Task task = taskRepository.findById(id);
        taskRepository.delete(task);
        return taskMapper.convertToTaskResponse(task);
    }

    @Override
    @Transactional
    public TaskResponse update(UpdateTaskRequest updateTaskRequest, String userEmail) {
        User user = userService.getByEmail(userEmail);
        validateTaskAndOwner(updateTaskRequest.getId(), user.getId());
        Task task = taskRepository.findById(updateTaskRequest.getId());
        task.setTitle(updateTaskRequest.getTitle())
                .setDescription(updateTaskRequest.getDescription())
                .setStatus(updateTaskRequest.getStatus());
        taskRepository.save(task);
        return taskMapper.convertToTaskResponse(task);
    }

    private void validateTaskAndOwner(Long taskId, Long userId) {
        if (!taskRepository.existsByIdAndOwnerId(taskId, userId)) {
            throw new TaskOwnershipException("Task does not belong to the current user");
        }
    }

}

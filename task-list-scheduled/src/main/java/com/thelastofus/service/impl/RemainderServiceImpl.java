package com.thelastofus.service.impl;

import com.thelastofus.dto.mail.MailType;
import com.thelastofus.dto.mail.Message;
import com.thelastofus.model.Status;
import com.thelastofus.model.Task;
import com.thelastofus.model.User;
import com.thelastofus.service.KafkaService;
import com.thelastofus.service.RemainderService;
import com.thelastofus.service.UserService;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RemainderServiceImpl implements RemainderService {

    static int MAX_TASKS_IN_MESSAGE = 5;

    UserService userService;
    KafkaService kafkaService;

    @Override
//    @Scheduled(cron = "0 0 * * * ?")
    @Scheduled(cron = "0 * * * * ?")
    public void remindForTask() {
        List<User> users = userService.getUsers();
        for (User user : users) {
            sendTaskRemainderForUser(user);
        }
    }
    private void sendTaskRemainderForUser(User user) {
        Map<Status, List<Task>> taskByStatus = user.getTasks().stream()
                .collect(Collectors.groupingBy(Task::getStatus));
        List<Task> completedTasks = taskByStatus.getOrDefault(Status.DONE, List.of());
        List<Task> incompletedTasks = user.getTasks().stream()
                .filter(task -> task.getStatus() != Status.DONE)
                .collect(Collectors.toList());

        String messageBody = formatTaskMessage(completedTasks, incompletedTasks);

        kafkaService.send(user.getEmail(), user.getUsername(), messageBody);
    }

    private String formatTaskMessage(List<Task> completedTasks, List<Task> incompletedTasks) {
        String completedTasksStr = formatTaskList("Completed tasks", completedTasks);
        String incompletedTasksStr = formatTaskList("Incompleted tasks", incompletedTasks);

        return String.format("%s\n\n%s", completedTasksStr, incompletedTasksStr);
    }

    private String formatTaskList(String title, List<Task> tasks) {
        String taskList = tasks.stream()
                .limit(MAX_TASKS_IN_MESSAGE)
                .map(Task::getTitle)
                .collect(Collectors.joining(", "));

        String ellipsis = tasks.size() > MAX_TASKS_IN_MESSAGE ? "..." : "";

        return String.format("%s (%d): %s%s", title, tasks.size(), taskList, ellipsis);
    }
}



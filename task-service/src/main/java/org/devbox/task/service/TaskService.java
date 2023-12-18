package org.devbox.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.devbox.task.model.Task;
import org.devbox.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.devbox.task.config.WebConfig;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;
    private final WebConfig webClient;

    @Value("${external.service.url}") // Add the URL of the external service in your application.properties
    private String externalServiceUrl;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    public Task createTask(Task task) {
        taskRepository.save(task);

        log.info("Task {} created", task.getId());
        return task;
    }

    // Other service methods
}
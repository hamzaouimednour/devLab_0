package org.devbox.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.devbox.task.model.Task;
import org.devbox.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.devbox.task.config.WebConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;
    private final WebConfig webClient;

    /*
    @Value("${external.service.url}") // Add the URL of the external service in your application.properties
    private String externalServiceUrl;
    */

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

    public Task updateTask(Long id, Task task) {
        Optional<Task> optionalExistingTask = taskRepository.findById(id);

        if (optionalExistingTask.isPresent()) {
            Task existingTask = optionalExistingTask.get();

            // Update fields
            existingTask.setName(task.getName());
            existingTask.setDescription(task.getDescription());
            existingTask.setUserId(task.getUserId());

            return taskRepository.save(existingTask);
        }

        return null; // Task with the given id not found
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> findAllByUser(Long userId) {
        return taskRepository.findAllByUserId(userId);
    }
}
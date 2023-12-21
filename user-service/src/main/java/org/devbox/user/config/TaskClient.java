package org.devbox.user.config;

import org.devbox.user.model.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "tasks", url="${application.config.tasks-url}")
public interface TaskClient {

    @GetMapping("/user/{id}")
    List<Task> findTasksByUser(@PathVariable Long id);
}

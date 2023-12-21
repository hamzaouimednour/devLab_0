package org.devbox.user.service;

import lombok.extern.slf4j.Slf4j;
import org.devbox.user.config.TaskClient;
import org.devbox.user.dto.UserTasksResponse;
import org.devbox.user.model.User;
import org.devbox.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskClient taskClient;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User createUser(User user) {
        userRepository.save(user);

        log.info("User {} created", user.getUsername());
        return user; 
    }

    public User updateUser(Long id, User user) {
        Optional<User> optionalExistingUser = userRepository.findById(id);

        if (optionalExistingUser.isPresent()) {
            User existingUser = optionalExistingUser.get();

            // Update fields
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());

            return userRepository.save(existingUser);
        }

        return null; // User with the given id not found
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserTasksResponse findUserWithTasks(Long id) {
        var user = userRepository.findById(id)
                .orElse(
                        User
                        .builder()
                        .build()
                );
        var userTasks = taskClient.findTasksByUser(id);
        log.debug("User "+id+" Tasks:", userTasks);
        return UserTasksResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .tasks(userTasks)
                .build();
    }
}
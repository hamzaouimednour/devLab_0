package org.devbox.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.devbox.user.model.Task;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTasksResponse {

    private Long id;
    private String fullName;
    private String email;
    private List<Task> tasks;
}

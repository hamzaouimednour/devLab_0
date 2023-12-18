package org.devbox.user.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    public User(long l, String user1) {
    }

    // Other fields, getters, and setters are generated using Lombok plugin
}
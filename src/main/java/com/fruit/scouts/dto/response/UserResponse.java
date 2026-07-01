package com.fruit.scouts.dto.response;

import com.fruit.scouts.model.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String role;
    private LocalDateTime createdAt;

    public static UserResponse from(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole().toString());
        response.setCreatedAt(user.getCreatedAt());

        return response;
    }

    public static List<UserResponse> from(List<User> users) {
        return users.stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }
}

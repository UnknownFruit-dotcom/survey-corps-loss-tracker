package com.fruit.scouts.dto.request;

import com.fruit.scouts.model.Role;

public record UserUpdateRequest(
        String username,
        Role role
) { }

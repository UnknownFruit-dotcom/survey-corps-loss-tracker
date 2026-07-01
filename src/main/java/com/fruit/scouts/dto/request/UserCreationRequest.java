package com.fruit.scouts.dto.request;

public record UserCreationRequest(
        String username,
        String password
) { }

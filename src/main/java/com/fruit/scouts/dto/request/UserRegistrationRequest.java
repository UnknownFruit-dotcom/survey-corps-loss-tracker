package com.fruit.scouts.dto.request;

public record UserRegistrationRequest(
        String username,
        String password
) { }
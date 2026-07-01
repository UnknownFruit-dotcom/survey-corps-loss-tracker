package com.fruit.scouts.dto.request;

public record UserLoginRequest(
        String username,
        String password
) { }

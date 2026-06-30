package com.fruit.scouts.dto.request;

public record TeamUpdateRequest(
        String name,
        String description,
        String status,
        Long leaderId
) { }

package com.fruit.scouts.dto.request;

public record TeamCreationRequest(
    String name,
    String description,
    String status,
    Long leaderId
) {}

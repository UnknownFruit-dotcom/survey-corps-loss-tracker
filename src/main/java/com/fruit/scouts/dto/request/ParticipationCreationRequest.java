package com.fruit.scouts.dto.request;

public record ParticipationCreationRequest(
        Long operationId,
        Long scoutId,
        String resultStatus,
        String notes
) { }

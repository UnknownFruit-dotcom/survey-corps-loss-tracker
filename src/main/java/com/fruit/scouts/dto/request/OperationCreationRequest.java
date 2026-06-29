package com.fruit.scouts.dto.request;

import java.time.LocalDateTime;

public record OperationCreationRequest(
        String type,
        String title,
        String description,
        Integer priority,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        Integer plannedParticipantsCount,
        Integer actualParticipantsCount,
        String status,
        String notes
) { }

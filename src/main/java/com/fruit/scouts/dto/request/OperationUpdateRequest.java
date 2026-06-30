package com.fruit.scouts.dto.request;

import com.fruit.scouts.model.OperationStatus;
import com.fruit.scouts.model.OperationType;

import java.time.LocalDateTime;

public record OperationUpdateRequest(
        OperationType type,
        String title,
        String description,
        Integer priority,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        Integer plannedParticipantsCount,
        Integer actualParticipantsCount,
        OperationStatus status,
        String notes
) { }

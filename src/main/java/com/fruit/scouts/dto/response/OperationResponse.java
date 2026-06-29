package com.fruit.scouts.dto.response;

import com.fruit.scouts.model.Operation;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OperationResponse {
    private Long id;
    private String type;
    private String title;
    private String description;
    private Integer priority;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Integer plannedParticipantsCount;
    private Integer actualParticipantsCount;
    private String status;
    private String notes;

    public static OperationResponse from(Operation operation) {
        OperationResponse response = new OperationResponse();
        response.setId(operation.getId());
        response.setType(operation.getType().toString());
        response.setTitle(operation.getTitle());
        response.setDescription(operation.getDescription());
        response.setPriority(operation.getPriority());
        response.setStartDateTime(operation.getStartDateTime());
        response.setEndDateTime(operation.getEndDateTime());
        response.setPlannedParticipantsCount(operation.getPlannedParticipantsCount());
        response.setActualParticipantsCount(operation.getActualParticipantsCount());
        response.setStatus(operation.getStatus().toString());
        response.setNotes(operation.getNotes());

        return response;
    }

    public static List<OperationResponse> from(List<Operation> operations) {
        return operations.stream()
                .map(OperationResponse::from)
                .collect(Collectors.toList());
    }
}

package com.fruit.scouts.dto.response;

import com.fruit.scouts.model.Participation;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ParticipationResponse {
    private Long id;
    private Long operationId;
    private Long scoutId;
    private String resultStatus;
    private String notes;

    public static ParticipationResponse from(Participation participation) {
        ParticipationResponse response = new ParticipationResponse();
        response.setId(participation.getId());
        response.setOperationId(participation.getId());
        response.setScoutId(participation.getScout().getId());
        response.setResultStatus(participation.getResultStatus().toString());
        response.setNotes(participation.getNotes());

        return response;
    }

    public static List<ParticipationResponse> from(List<Participation> participations) {
        return participations.stream()
                .map(ParticipationResponse::from)
                .collect(Collectors.toList());
    }
}

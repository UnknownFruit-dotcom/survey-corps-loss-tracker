package com.fruit.scouts.dto.response;

import com.fruit.scouts.model.Scout;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ScoutResponse {
    private Long id;
    private String name;
    private Long teamId;
    private LocalDate joinedAt;
    private String rank;
    private String position;
    private Integer totalMissions;
    private String status;
    private String notes;

    public static ScoutResponse from(Scout scout) {
        ScoutResponse response = new ScoutResponse();
        response.setId(scout.getId());
        response.setName(scout.getName());
        response.setTeamId(scout.getTeam().getId());
        response.setJoinedAt(scout.getJoinedAt());
        response.setRank(scout.getRank() != null ? scout.getRank().name() : null);
        response.setPosition(scout.getPosition() != null ? scout.getPosition().name() : null);
        response.setTotalMissions(scout.getTotalMissions());
        response.setStatus(scout.getStatus() != null ? scout.getStatus().name() : null);
        response.setNotes(scout.getNotes());

        return response;
    }

    public static List<ScoutResponse> from(List<Scout> scouts) {
        return scouts.stream()
                .map(ScoutResponse::from)
                .collect(Collectors.toList());
    }
}

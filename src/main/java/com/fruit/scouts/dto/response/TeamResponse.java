package com.fruit.scouts.dto.response;

import com.fruit.scouts.model.Team;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TeamResponse {
    private Long id;
    private String name;
    private String description;
    private String status;
    private Long leaderId;
    private String leaderName;

    public static TeamResponse from(Team team) {
        TeamResponse response = new TeamResponse();
        response.setId(team.getId());
        response.setName(team.getName());
        response.setDescription(team.getDescription());
        response.setStatus(team.getStatus().toString());

        if (team.getLeader() != null) {
            response.setLeaderId(team.getLeader().getId());
            response.setLeaderName(team.getLeader().getName());
        }

        return response;
    }

    public static List<TeamResponse> from(List<Team> teams) {
        return teams.stream()
                .map(TeamResponse::from)
                .collect(Collectors.toList());
    }
}

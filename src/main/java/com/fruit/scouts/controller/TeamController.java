package com.fruit.scouts.controller;

import com.fruit.scouts.dto.request.TeamCreationRequest;
import com.fruit.scouts.dto.response.TeamResponse;
import com.fruit.scouts.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamResponse> createTeam(@RequestBody TeamCreationRequest request) {
        return ResponseEntity.ok(teamService.createTeam(request));
    }

    @GetMapping
    public ResponseEntity<List<TeamResponse>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponse> getTeamById(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getTeamById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}

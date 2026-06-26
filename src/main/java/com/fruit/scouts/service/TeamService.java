package com.fruit.scouts.service;

import com.fruit.scouts.dto.request.TeamCreationRequest;
import com.fruit.scouts.dto.response.TeamResponse;
import com.fruit.scouts.exception.ResourceNotFoundException;
import com.fruit.scouts.mapper.TeamMapper;
import com.fruit.scouts.model.Team;
import com.fruit.scouts.repository.ScoutRepository;
import com.fruit.scouts.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {

    private final TeamRepository teamRepository;
    private final ScoutRepository scoutRepository;
    private final TeamMapper teamMapper;

    @Transactional
    public TeamResponse createTeam(TeamCreationRequest request) {
        var team = teamMapper.toEntity(request);

        if (request.leaderId() != null) {
            scoutRepository.findById(request.leaderId()).ifPresent(team::setLeader);
        }

        Team saved = teamRepository.save(team);
        return TeamResponse.from(saved);
    }

    public List<TeamResponse> getAllTeams() {
        return TeamResponse.from(teamRepository.findAll());
    }

    public TeamResponse getTeamById(Long id) { return TeamResponse.from(teamRepository.getById(id)); }

    @Transactional
    public void deleteTeam(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));

        teamRepository.delete(team);
    }
}

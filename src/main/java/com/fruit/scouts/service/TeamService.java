package com.fruit.scouts.service;

import com.fruit.scouts.dto.request.TeamCreationRequest;
import com.fruit.scouts.dto.request.TeamLeaderUpdateRequest;
import com.fruit.scouts.dto.request.TeamUpdateRequest;
import com.fruit.scouts.dto.response.TeamResponse;
import com.fruit.scouts.exception.ResourceNotFoundException;
import com.fruit.scouts.mapper.TeamMapper;
import com.fruit.scouts.model.Scout;
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

    @Transactional
    public TeamResponse updateTeam(Long id, TeamUpdateRequest request) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));

        teamMapper.updateTeamFromDto(request, team);

        if (request.leaderId() != null && !team.getLeader().getId().equals(request.leaderId())) {
            Scout scout = scoutRepository.findById(request.leaderId())
                    .orElseThrow(() -> new ResourceNotFoundException("Scout not found with id: " + request.leaderId()));
            team.setLeader(scout);
        }

        return TeamResponse.from(teamRepository.save(team));
    }

    @Transactional
    public TeamResponse updateLeader(Long id, TeamLeaderUpdateRequest request) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));

        if (request.scoutId() != null) {
            Scout scout = scoutRepository.findById(request.scoutId())
                    .orElseThrow(() -> new ResourceNotFoundException("Scout not found with id: " + request.scoutId()));
            team.setLeader(scout);
        } else {
            team.setLeader(null);
        }

        return TeamResponse.from(teamRepository.save(team));
    }
}

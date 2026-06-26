package com.fruit.scouts.service;

import com.fruit.scouts.dto.request.ScoutCreationRequest;
import com.fruit.scouts.dto.response.ScoutResponse;
import com.fruit.scouts.exception.ResourceNotFoundException;
import com.fruit.scouts.mapper.ScoutMapper;
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
public class ScoutService {
    private final ScoutRepository scoutRepository;
    private final TeamRepository teamRepository;
    private final ScoutMapper scoutMapper;

    @Transactional
    public ScoutResponse createScout(ScoutCreationRequest request) {
        var scout = scoutMapper.toEntity(request);

        if (request.teamId() != null) {
            Team team = teamRepository.findById(request.teamId())
                    .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + request.teamId()));
            scout.setTeam(team);
        } else {
            throw new IllegalArgumentException("TeamId is required");
        }

        if (scout.getTotalMissions() == null) {
            scout.setTotalMissions(0);
        }

        Scout saved = scoutRepository.save(scout);
        return ScoutResponse.from(saved);
    }

    public List<ScoutResponse> getAllScouts() {
        return ScoutResponse.from(scoutRepository.findAll());
    }

    public ScoutResponse getScoutById(Long id) { return ScoutResponse.from(scoutRepository.getById(id)); }

    @Transactional
    public void deleteScout(Long id) {
        Scout scout = scoutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Scout not found with id: " + id));

        scoutRepository.delete(scout);
    }
}

package com.fruit.scouts.service;

import com.fruit.scouts.dto.request.ParticipationCreationRequest;
import com.fruit.scouts.dto.request.ParticipationNotesUpdateRequest;
import com.fruit.scouts.dto.response.ParticipationResponse;
import com.fruit.scouts.exception.ResourceNotFoundException;
import com.fruit.scouts.exception.UnavailableScoutException;
import com.fruit.scouts.mapper.ParticipationMapper;
import com.fruit.scouts.model.Operation;
import com.fruit.scouts.model.Participation;
import com.fruit.scouts.model.Scout;
import com.fruit.scouts.model.ScoutStatus;
import com.fruit.scouts.repository.OperationRepository;
import com.fruit.scouts.repository.ParticipationRepository;
import com.fruit.scouts.repository.ScoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    private final ScoutRepository scoutRepository;
    private final OperationRepository operationRepository;
    private final ParticipationMapper participationMapper;

    @Transactional
    public ParticipationResponse createParticipation(ParticipationCreationRequest request) {
        Operation operation = operationRepository.findById(request.operationId())
                .orElseThrow(() -> new ResourceNotFoundException("Operation not found"));

        Scout scout = scoutRepository.findById(request.scoutId())
                .orElseThrow(() -> new ResourceNotFoundException("Scout not found"));

        if (scout.getStatus() != ScoutStatus.ACTIVE && scout.getStatus() != ScoutStatus.WOUNDED) {
            throw new UnavailableScoutException("Scout is not available: " + scout.getStatus());
        }

        Participation participation = participationMapper.toEntity(request);
        participation.setOperation(operation);
        participation.setScout(scout);

        if (Boolean.TRUE.equals(request.considerStatus())) {
            scout.setStatus(request.resultStatus());
        }

        if (Boolean.TRUE.equals(request.countsTowardTotal())) {
            scout.setTotalMissions(scout.getTotalMissions() + 1);
        }

        Participation saved = participationRepository.save(participation);
        return ParticipationResponse.from(saved);
    }

    public List<ParticipationResponse> getAllParticipations() {
        return ParticipationResponse.from(participationRepository.findAll());
    }

    public ParticipationResponse getParticipationById(Long id) { return ParticipationResponse.from(participationRepository.getById(id)); }

    @Transactional
    public void deleteParticipation(Long id) {
        Participation participation = participationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participation not found with id: " + id));

        if (participation.getCountsTowardTotal()) {
            Scout scout = scoutRepository.findById(participation.getScout().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Scout not found"));

            scout.setTotalMissions(scout.getTotalMissions() - 1);
        }

        participationRepository.delete(participation);
    }

    @Transactional
    public ParticipationResponse changeParticipationNotes(Long id, ParticipationNotesUpdateRequest request) {
        Participation participation = participationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participation not found with id: " + id));

        participation.setNotes(request.notes());
        return ParticipationResponse.from(participationRepository.save(participation));
    }
}

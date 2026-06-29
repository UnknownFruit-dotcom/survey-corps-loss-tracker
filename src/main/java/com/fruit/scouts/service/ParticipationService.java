package com.fruit.scouts.service;

import com.fruit.scouts.dto.request.ParticipationCreationRequest;
import com.fruit.scouts.dto.response.ParticipationResponse;
import com.fruit.scouts.exception.ResourceNotFoundException;
import com.fruit.scouts.mapper.ParticipationMapper;
import com.fruit.scouts.model.Operation;
import com.fruit.scouts.model.Participation;
import com.fruit.scouts.model.Scout;
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
        var participation = participationMapper.toEntity(request);

        if (request.scoutId() != null) {
            Scout scout = scoutRepository.findById(request.scoutId())
                    .orElseThrow(() -> new ResourceNotFoundException("Scout not found with id: " + request.scoutId()));
            participation.setScout(scout);
        } else {
            throw new IllegalArgumentException("ScoutId is required");
        }

        if (request.operationId() != null) {
            Operation operation = operationRepository.findById(request.operationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Operation not found with id: " + request.operationId()));
            participation.setOperation(operation);
        } else {
            throw new IllegalArgumentException("OperationId is required");
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

        participationRepository.delete(participation);
    }
}

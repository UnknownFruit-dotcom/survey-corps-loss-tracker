package com.fruit.scouts.service;

import com.fruit.scouts.dto.request.OperationCreationRequest;
import com.fruit.scouts.dto.request.OperationStatusUpdateRequest;
import com.fruit.scouts.dto.request.OperationUpdateRequest;
import com.fruit.scouts.dto.response.OperationResponse;
import com.fruit.scouts.exception.ResourceNotFoundException;
import com.fruit.scouts.mapper.OperationMapper;
import com.fruit.scouts.model.Operation;
import com.fruit.scouts.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OperationService {
    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;

    @Transactional
    public OperationResponse createOperation(OperationCreationRequest request) {
        var operation = operationMapper.toEntity(request);

        if (operation.getPriority() == null) {
            operation.setPriority(3);
        }

        if (operation.getActualParticipantsCount() == null) {
            operation.setActualParticipantsCount(0);
        }

        if (operation.getPlannedParticipantsCount() == null) {
            operation.setPlannedParticipantsCount(0);
        }

        Operation saved = operationRepository.save(operation);
        return OperationResponse.from(saved);
    }

    public List<OperationResponse> getAllOperations() {
        return OperationResponse.from(operationRepository.findAll());
    }

    public OperationResponse getOperationById(Long id) { return OperationResponse.from(operationRepository.getById(id)); }

    @Transactional
    public void deleteOperation(Long id) {
        Operation operation = operationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Operation not found with id: " + id));

        operationRepository.delete(operation);
    }

    @Transactional
    public OperationResponse updateOperation(Long id, OperationUpdateRequest request) {
        Operation operation = operationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Operation not found with id: " + id));

        operationMapper.updateOperationFromDto(request, operation);

        return OperationResponse.from(operationRepository.save(operation));
    }

    @Transactional
    public OperationResponse updateStatus(Long id, OperationStatusUpdateRequest request) {
        Operation operation = operationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Operation not found with id: " + id));

        operation.setStatus(request.status());
        return OperationResponse.from(operationRepository.save(operation));
    }
}

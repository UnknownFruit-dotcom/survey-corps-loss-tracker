package com.fruit.scouts.controller;

import com.fruit.scouts.dto.request.OperationCreationRequest;
import com.fruit.scouts.dto.response.OperationResponse;
import com.fruit.scouts.service.OperationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operations")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;

    @PostMapping
    public ResponseEntity<OperationResponse> createOperation(@RequestBody OperationCreationRequest request) {
        return ResponseEntity.ok(operationService.createOperation(request));
    }

    @GetMapping
    public ResponseEntity<List<OperationResponse>> getAllOperations() {
        return ResponseEntity.ok(operationService.getAllOperations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationResponse> getOperationById(@PathVariable Long id) {
        return ResponseEntity.ok(operationService.getOperationById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperation(@PathVariable Long id) {
        operationService.deleteOperation(id);
        return ResponseEntity.noContent().build();
    }
}

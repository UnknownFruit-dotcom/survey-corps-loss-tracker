package com.fruit.scouts.controller;

import com.fruit.scouts.dto.request.ScoutCreationRequest;
import com.fruit.scouts.dto.request.ScoutPositionUpdateRequest;
import com.fruit.scouts.dto.request.ScoutStatusUpdateRequest;
import com.fruit.scouts.dto.request.ScoutUpdateRequest;
import com.fruit.scouts.dto.response.ScoutResponse;
import com.fruit.scouts.model.ScoutStatus;
import com.fruit.scouts.service.ScoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/scouts")
@RequiredArgsConstructor
public class ScoutController {

    private final ScoutService scoutService;

    @PostMapping
    public ResponseEntity<ScoutResponse> createScout(@RequestBody ScoutCreationRequest request) {
        log.info("Received date: {}", request.joinedAt());
        log.warn("Request: {}", request);
        return ResponseEntity.ok(scoutService.createScout(request));
    }

    @GetMapping
    public ResponseEntity<List<ScoutResponse>> getAllScouts() {
        return ResponseEntity.ok(scoutService.getAllScouts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScoutResponse> getScoutById(@PathVariable Long id) {
        return ResponseEntity.ok(scoutService.getScoutById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScout(@PathVariable Long id) {
        scoutService.deleteScout(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScoutResponse> updateScout(@PathVariable Long id, @RequestBody ScoutUpdateRequest request) {
        return ResponseEntity.ok(scoutService.updateScout(id, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ScoutResponse> changeScoutStatus(@PathVariable Long id, @RequestBody ScoutStatusUpdateRequest request) {
        return ResponseEntity.ok(scoutService.changeScoutStatus(id, request));
    }

    @PatchMapping("/{id}/position")
    public ResponseEntity<ScoutResponse> changeScoutPosition(@PathVariable Long id, @RequestBody ScoutPositionUpdateRequest request) {
        return ResponseEntity.ok(scoutService.changeScoutPosition(id, request));
    }
}

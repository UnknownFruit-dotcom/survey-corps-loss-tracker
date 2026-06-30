package com.fruit.scouts.controller;

import com.fruit.scouts.dto.request.ParticipationCreationRequest;
import com.fruit.scouts.dto.request.ParticipationNotesUpdateRequest;
import com.fruit.scouts.dto.response.ParticipationResponse;
import com.fruit.scouts.service.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participations")
@RequiredArgsConstructor
public class ParticipationController {

    private final ParticipationService participationService;

    @PostMapping
    public ResponseEntity<ParticipationResponse> createParticipation(@RequestBody ParticipationCreationRequest request) {
        return ResponseEntity.ok(participationService.createParticipation(request));
    }

    @GetMapping
    public ResponseEntity<List<ParticipationResponse>> getAllParticipations() {
        return ResponseEntity.ok(participationService.getAllParticipations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipationResponse> getParticipationById(@PathVariable Long id) {
        return ResponseEntity.ok(participationService.getParticipationById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipation(@PathVariable Long id) {
        participationService.deleteParticipation(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/notes")
    public ResponseEntity<ParticipationResponse> updateNotes(@PathVariable Long id, @RequestBody ParticipationNotesUpdateRequest request) {
        return ResponseEntity.ok(participationService.updateNotes(id, request));
    }
}

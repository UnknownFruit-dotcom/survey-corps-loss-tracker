package com.fruit.scouts.dto.request;

import java.time.LocalDate;

public record ScoutCreationRequest(
        String name,
        Long teamId,
        LocalDate joinedAt,
        String rank,
        String position,
        Integer totalMissions,
        String status,
        String notes
) { }

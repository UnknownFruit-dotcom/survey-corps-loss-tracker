package com.fruit.scouts.dto.request;

import com.fruit.scouts.model.Position;
import com.fruit.scouts.model.Rank;
import com.fruit.scouts.model.ScoutStatus;

import java.time.LocalDate;

public record ScoutUpdateRequest(
        String name,
        Long teamId,
        LocalDate joinedAt,
        Rank rank,
        Position position,
        Integer totalMissions,
        ScoutStatus status,
        String notes
) {
}

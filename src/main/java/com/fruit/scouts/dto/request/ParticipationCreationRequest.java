package com.fruit.scouts.dto.request;

import com.fruit.scouts.model.ScoutStatus;

public record ParticipationCreationRequest(
        Long operationId,
        Long scoutId,
        ScoutStatus resultStatus,
        String notes,
        Boolean considerStatus,
        Boolean countsTowardTotal
) {
    public Boolean considerStatus() {
        return considerStatus != null ? considerStatus : true;
    }

    public Boolean countsTowardTotal() {
        return countsTowardTotal != null ? countsTowardTotal : true;
    }
}

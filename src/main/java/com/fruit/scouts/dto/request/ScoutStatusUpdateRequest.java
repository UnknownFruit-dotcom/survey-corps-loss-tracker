package com.fruit.scouts.dto.request;

import com.fruit.scouts.model.ScoutStatus;

public record ScoutStatusUpdateRequest(
        ScoutStatus status
) { }

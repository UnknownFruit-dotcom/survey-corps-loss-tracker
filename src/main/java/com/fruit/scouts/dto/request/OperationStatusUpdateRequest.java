package com.fruit.scouts.dto.request;

import com.fruit.scouts.model.OperationStatus;

public record OperationStatusUpdateRequest(
    OperationStatus status
) { }

package com.fruit.scouts.mapper;

import com.fruit.scouts.dto.request.OperationCreationRequest;
import com.fruit.scouts.dto.response.OperationResponse;
import com.fruit.scouts.model.Operation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OperationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "participations", ignore = true)
Operation toEntity(OperationCreationRequest request);

OperationResponse toResponse(Operation operation);

List<OperationResponse> toResponseList(List<Operation> operations);
}

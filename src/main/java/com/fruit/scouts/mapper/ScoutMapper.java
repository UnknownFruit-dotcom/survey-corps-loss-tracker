package com.fruit.scouts.mapper;

import com.fruit.scouts.dto.request.ScoutCreationRequest;
import com.fruit.scouts.dto.request.ScoutUpdateRequest;
import com.fruit.scouts.dto.response.ScoutResponse;
import com.fruit.scouts.model.Scout;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ScoutMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "team", ignore = true)
    @Mapping(target = "joinedAt", source = "joinedAt")
    Scout toEntity(ScoutCreationRequest request);

    ScoutResponse toResponse(Scout scout);

    List<ScoutResponse> toResponseList(List<Scout> scouts);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "team", ignore = true)
    void updateScoutFromDto(ScoutUpdateRequest dto, @MappingTarget Scout entity);
}

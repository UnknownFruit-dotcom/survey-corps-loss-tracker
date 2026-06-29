package com.fruit.scouts.mapper;

import com.fruit.scouts.dto.request.ParticipationCreationRequest;
import com.fruit.scouts.dto.response.ParticipationResponse;
import com.fruit.scouts.model.Participation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParticipationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "scout", ignore = true)
    @Mapping(target = "operation", ignore = true)
    Participation toEntity(ParticipationCreationRequest request);

    ParticipationResponse toResponse(Participation participation);

    List<ParticipationResponse> toResponseList(List<Participation> participations);
}

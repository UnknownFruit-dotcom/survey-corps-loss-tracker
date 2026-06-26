package com.fruit.scouts.mapper;

import com.fruit.scouts.dto.request.TeamCreationRequest;
import com.fruit.scouts.dto.response.TeamResponse;
import com.fruit.scouts.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "leader", ignore = true)
    @Mapping(target = "members", ignore = true)
    Team toEntity(TeamCreationRequest request);

    TeamResponse toResponse(Team team);

    List<TeamResponse> toResponseList(List<Team> teams);
}
package com.fruit.scouts.mapper;

import com.fruit.scouts.dto.request.TeamCreationRequest;
import com.fruit.scouts.dto.request.TeamUpdateRequest;
import com.fruit.scouts.dto.response.TeamResponse;
import com.fruit.scouts.model.Team;
import org.mapstruct.*;

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

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "leader", ignore = true)
    @Mapping(target = "members", ignore = true)
    void updateTeamFromDto(TeamUpdateRequest dto, @MappingTarget Team entity);
}
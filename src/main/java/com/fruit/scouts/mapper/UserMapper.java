package com.fruit.scouts.mapper;

import com.fruit.scouts.dto.request.UserCreationRequest;
import com.fruit.scouts.dto.request.UserUpdateRequest;
import com.fruit.scouts.dto.response.UserResponse;
import com.fruit.scouts.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    User toEntity(UserCreationRequest request);

    UserResponse toResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserUpdateRequest dto, @MappingTarget User entity);
}

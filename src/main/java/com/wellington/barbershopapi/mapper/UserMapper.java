package com.wellington.barbershopapi.mapper;

import com.wellington.barbershopapi.dto.request.UserCreateRequest;
import com.wellington.barbershopapi.dto.response.UserResponse;
import com.wellington.barbershopapi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserCreateRequest dto);

    UserResponse toDTO(User user);

}
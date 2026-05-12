package com.wellington.barbershopapi.controller;

import com.wellington.barbershopapi.dto.request.UserCreateRequest;
import com.wellington.barbershopapi.dto.response.UserResponse;
import com.wellington.barbershopapi.mapper.UserMapper;
import com.wellington.barbershopapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper mapper;
    private final UserService service;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody @Valid UserCreateRequest userRequest){
       // Lógica para criar um novo usuário
       return service.create(userRequest);
    }

}

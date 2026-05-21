package com.wellington.barbershopapi.controller;

import com.wellington.barbershopapi.dto.request.UserCreateRequest;
import com.wellington.barbershopapi.dto.response.UserResponse;
import com.wellington.barbershopapi.mapper.UserMapper;
import com.wellington.barbershopapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody @Valid UserCreateRequest userRequest){
       // Lógica para criar um novo usuário
       return service.create(userRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID id){
        // Lógica para deletar um usuário por email
        service.deleteById(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(@PathVariable UUID id){
        // Lógica para obter um usuário por email
        return service.obterPorId(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UserResponse> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size){
        // Lógica para obter todos os usuários
        return service.obterTodosUsuarios(page, size);
    }

}

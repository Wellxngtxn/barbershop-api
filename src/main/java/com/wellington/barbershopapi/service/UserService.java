package com.wellington.barbershopapi.service;

import com.wellington.barbershopapi.dto.request.UserCreateRequest;
import com.wellington.barbershopapi.dto.response.UserResponse;
import com.wellington.barbershopapi.enums.AuthProvider;
import com.wellington.barbershopapi.enums.Role;
import com.wellington.barbershopapi.exception.EmailAlreadyExistsException;
import com.wellington.barbershopapi.exception.ResourceNotFoundException;
import com.wellington.barbershopapi.mapper.UserMapper;
import com.wellington.barbershopapi.entity.User;
import com.wellington.barbershopapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;

    public UserResponse create(UserCreateRequest request){
        if(repository.existsByEmail(request.email())){
            throw new EmailAlreadyExistsException("Email já existente!");
        }

        User user = mapper.toEntity(request);
        user.setPassword(encoder.encode(request.password()));
        user.setRole(Role.CLIENT);
        user.setProvider(AuthProvider.LOCAL);

        User savedUser = repository.save(user);

        return mapper.toDTO(savedUser);
    }

    public UserResponse obterPorEmail(String email){
        User user = repository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado!"));
        return mapper.toDTO(user);
    }

    public void deleteByEmail(String email){
        Optional<User> user = repository.findByEmail(email);
        if(user.isEmpty()){
            throw new ResourceNotFoundException("Usuário não encontrado!");
        }
        repository.delete(user.get());
    }

}

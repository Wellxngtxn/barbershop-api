package com.wellington.barbershopapi.service;

import com.wellington.barbershopapi.dto.request.UserCreateRequest;
import com.wellington.barbershopapi.dto.response.UserResponse;
import com.wellington.barbershopapi.enums.UserRoles.AuthProvider;
import com.wellington.barbershopapi.enums.UserRoles.Role;
import com.wellington.barbershopapi.mapper.UserMapper;
import com.wellington.barbershopapi.model.User;
import com.wellington.barbershopapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;

    public UserResponse create(UserCreateRequest request){
        if(repository.existsByEmail(request.email())){
            throw new RuntimeException("Email já existente!");
        }

        User user = mapper.toEntity(request);
        user.setPassword(encoder.encode(request.password()));
        user.setRole(Role.CLIENT);
        user.setProvider(AuthProvider.LOCAL);

        User savedUser = repository.save(user);

        return mapper.toDTO(savedUser);
    }

    public UserResponse obterPorEmail(String email){
        User user = repository.findByEmail(email);
        if(user == null){
            throw new RuntimeException("Usuário não encontrado!");
        }
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

}

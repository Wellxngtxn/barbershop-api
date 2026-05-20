package com.wellington.barbershopapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
        @NotBlank(message = "campo obrigatório")
        @Size(min = 2, max = 100, message = "campo fora do tamanho padrão")
        String name,

        @NotBlank(message = "campo obrigatório")
        @Size(min = 8, max = 150, message = "campo fora do tamanho padrão")
        @Email(message = "email inválido")
        String email,

        @NotBlank(message = "campo obrigatório")
        @Size(min = 8, max = 150, message = "campo fora do tamanho padrão")
        String password) {
}

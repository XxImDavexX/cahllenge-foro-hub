package com.challengeforohub.Challenge.Foro.Hub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DataSetUsuario(@NotBlank String nombre,
                             @NotBlank @Email String email,
                             @NotBlank String password) {
}

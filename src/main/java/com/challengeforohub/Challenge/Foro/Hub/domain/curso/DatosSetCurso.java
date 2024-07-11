package com.challengeforohub.Challenge.Foro.Hub.domain.curso;

import jakarta.validation.constraints.NotNull;

public record DatosSetCurso(@NotNull String nombre,
                            @NotNull Categoria categoria) {
}

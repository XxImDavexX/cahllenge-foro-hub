package com.challengeforohub.Challenge.Foro.Hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosPutTopico(
                             @NotBlank String titulo,
                             @NotBlank String mensaje,
                             @NotNull Long idCurso
                              ) {
}

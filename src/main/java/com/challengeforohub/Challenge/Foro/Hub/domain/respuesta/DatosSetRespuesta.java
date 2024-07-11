package com.challengeforohub.Challenge.Foro.Hub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosSetRespuesta(@NotBlank String mensaje,
                                @NotNull Long idTopico,
                                @NotBlank LocalDateTime fechaCreacion,
                                @NotNull Long idUsuario,
                                @NotBlank String Solucion) {
}

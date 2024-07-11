package com.challengeforohub.Challenge.Foro.Hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record DatosRegistroTopico(@NotBlank String titulo,
                                  @NotBlank String mensaje,
                                  LocalDateTime fechaCreacion,
                                  @NotNull boolean estatus,
                                  @NotNull Long idUsuario,
                                  @NotNull Long idCurso
                                  ) {
}

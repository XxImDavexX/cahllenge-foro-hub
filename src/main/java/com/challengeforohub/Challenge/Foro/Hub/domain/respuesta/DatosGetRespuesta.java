package com.challengeforohub.Challenge.Foro.Hub.domain.respuesta;

import java.time.LocalDateTime;

public record DatosGetRespuesta(Long id,
                                String mensaje,
                                Long idTopico,
                                LocalDateTime fechaCreacion,
                                Long idUsuario,
                                String solucion) {
}

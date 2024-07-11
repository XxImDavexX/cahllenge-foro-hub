package com.challengeforohub.Challenge.Foro.Hub.domain.topico;

import java.time.LocalDateTime;
import java.util.List;

public record DatosGetTopico(Long id,
                             String titulo,
                             String mensaje,
                             LocalDateTime fechaCreacion,
                             boolean estatus,
                             Long idUsuario,
                             Long idCurso) {
    public DatosGetTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.isEstatus(), topico.getUsuario().getId(), topico.getCurso().getId());
    }
}

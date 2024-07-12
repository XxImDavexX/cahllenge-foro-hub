package com.challengeforohub.Challenge.Foro.Hub.domain.topico;

import com.challengeforohub.Challenge.Foro.Hub.domain.curso.Curso;
import com.challengeforohub.Challenge.Foro.Hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity( name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private boolean estatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;


    public void actualizarDatos(DatosPutTopico datosPutTopico) {
        if (datosPutTopico.titulo() != null){
            this.titulo = datosPutTopico.titulo();
        }
        if (datosPutTopico.mensaje() != null){
            this.mensaje = datosPutTopico.mensaje();
        }
        this.fechaCreacion = LocalDateTime.now(Clock.systemDefaultZone());

    }
}

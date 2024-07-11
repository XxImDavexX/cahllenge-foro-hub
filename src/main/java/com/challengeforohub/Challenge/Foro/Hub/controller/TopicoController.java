package com.challengeforohub.Challenge.Foro.Hub.controller;


import com.challengeforohub.Challenge.Foro.Hub.domain.curso.CursoRepository;
import com.challengeforohub.Challenge.Foro.Hub.domain.topico.*;
import com.challengeforohub.Challenge.Foro.Hub.domain.usuario.UsuarioRepository;
import com.challengeforohub.Challenge.Foro.Hub.infrastructure.errores.ValidacionIntegridad;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    CursoRepository cursoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;


    @PostMapping
    @Transactional
    public ResponseEntity<DatosGetTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico){

        var curso = cursoRepository.findById(datosRegistroTopico.idCurso()).get();
        var fechaHora = LocalDateTime.now(Clock.systemDefaultZone());
        var usuario = usuarioRepository.findById(datosRegistroTopico.idUsuario()).get();

        var topico = new Topico(null,datosRegistroTopico.titulo(), datosRegistroTopico.mensaje(), fechaHora,
                datosRegistroTopico.estatus(),usuario,curso);

        topicoRepository.save(topico);

        DatosGetTopico datosGetTopico = new DatosGetTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(), topico.isEstatus(), topico.getUsuario().getId(), topico.getCurso().getId());


        return ResponseEntity.ok().body(datosGetTopico);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity mostarTodosLosTopicos(){

        Stream<Object> datosGetTopicos = topicoRepository.findAll().stream().map(DatosGetTopico::new);

        return ResponseEntity.ok(datosGetTopicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosGetTopico> mostrarTopicoPorId(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosGetTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(), topico.isEstatus(), topico.getUsuario().getId(), topico.getCurso().getId());

        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosPutTopico datosPutTopico, @PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarDatos(datosPutTopico);

        return  ResponseEntity.ok(new DatosGetTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(), topico.isEstatus(), topico.getUsuario().getId(), topico.getCurso().getId()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarTopico(@PathVariable Long id){

        if(!topicoRepository.findById(id).isPresent()){
            throw new ValidacionIntegridad("Este ID para Topico no fue encontrado en la DB");
        }

        Topico topico = topicoRepository.getReferenceById(id);

        topicoRepository.deleteById(id);

        var datosEliminados = new DatosGetTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.isEstatus(), topico.getUsuario().getId(), topico.getCurso().getId());

        return ResponseEntity.ok("El Topico fue borrado con exito de la DB\n " + datosEliminados);
    }



}

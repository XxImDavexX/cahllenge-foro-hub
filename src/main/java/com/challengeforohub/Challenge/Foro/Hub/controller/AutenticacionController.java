package com.challengeforohub.Challenge.Foro.Hub.controller;

import com.challengeforohub.Challenge.Foro.Hub.domain.usuario.DatosAutenticacionUsuario;
import com.challengeforohub.Challenge.Foro.Hub.domain.usuario.Usuario;
import com.challengeforohub.Challenge.Foro.Hub.infrastructure.security.DatosJWTToken;
import com.challengeforohub.Challenge.Foro.Hub.infrastructure.security.TokenServise;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenServise tokenServise;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.email(),
                datosAutenticacionUsuario.password());
        var usuarioAutenticado = authenticationManager.authenticate(authenticationToken);
        var JWTToken = tokenServise.getToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new DatosJWTToken(JWTToken));

    }
}

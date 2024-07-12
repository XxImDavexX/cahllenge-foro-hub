package com.challengeforohub.Challenge.Foro.Hub.infrastructure.security;

import com.challengeforohub.Challenge.Foro.Hub.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenServise tokenServise;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Obtenemos el token del header
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null){
            authHeader = authHeader.replace("Bearer ", "");
            var subject = tokenServise.getSubject(authHeader);
            if(subject != null){
                //token valido
                var usuario = usuarioRepository.findByEmail(subject);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities());//Se fuerza un inicio de sesion.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request,response);

    }
}



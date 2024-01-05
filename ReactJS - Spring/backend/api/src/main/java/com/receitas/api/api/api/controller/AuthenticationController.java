package com.receitas.api.api.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.receitas.api.api.api.dto.DadosAuthentication;
import com.receitas.api.api.domain.entities.Usuario;
import com.receitas.api.api.domain.services.TokenService;
import com.receitas.api.api.infra.core.properties.StorageProvider;
import com.receitas.api.api.infra.security.DadosTokenJWT;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthenticationController {

   
    private final AuthenticationManager manager;

    private final TokenService tokenService;

    private final StorageProvider storageProvider;

    @PostMapping("/login")
    public ResponseEntity<DadosTokenJWT> login(@RequestBody @Valid DadosAuthentication dados) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
            var authentication = manager.authenticate(authenticationToken);
            var tokenJWT = tokenService.getToken((Usuario) authentication.getPrincipal());
            String nome = ((Usuario) authentication.getPrincipal()).getNome();
            String photo = storageProvider.getImage().getDownloadUrl() + ((Usuario) authentication.getPrincipal()).getPhoto().getId().toString() + "/" + ((Usuario) authentication.getPrincipal()).getPhoto().getFileName();

            Long id = ((Usuario) authentication.getPrincipal()).getId();

            return ResponseEntity.ok(new DadosTokenJWT(tokenJWT, nome, photo, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

}
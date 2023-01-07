package com.receitaslivres.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.receitaslivres.api.dto.DadosPerfil;
import com.receitaslivres.api.entities.TiposUser;
import com.receitaslivres.api.repositories.PerfilRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/addtipo")
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    @Transactional
    @PostMapping
    public void cadastraperfil(@RequestBody @Valid DadosPerfil perfil) {
        TiposUser tipo = new TiposUser(perfil);
        perfilRepository.save(tipo);
    }
}

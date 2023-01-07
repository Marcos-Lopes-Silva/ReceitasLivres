package com.receitaslivres.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.receitaslivres.api.dto.DadosCadastroReceita;
import com.receitaslivres.api.entities.Receitas;
import com.receitaslivres.api.repositories.ReceitasRepository;

import jakarta.validation.Valid;

@RequestMapping("/receitas")
@RestController
public class ReceitasController {

    @Autowired
    private ReceitasRepository repository;

    public ResponseEntity createReceita(@RequestBody @Valid DadosCadastroReceita dados) {
        Receitas receitas = new Receitas(dados);

        repository.save(receitas);

        return ResponseEntity.ok(null);
    }
}

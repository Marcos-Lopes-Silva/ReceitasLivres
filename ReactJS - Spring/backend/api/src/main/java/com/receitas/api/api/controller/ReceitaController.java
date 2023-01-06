package com.receitas.api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.receitas.api.api.dto.ReceitaDto;
import com.receitas.api.api.entities.Receitas;
import com.receitas.api.api.repository.ClienteRepository;
import com.receitas.api.api.repository.ReceitaRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaRepository repository;
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/novaReceita")
    @Transactional
    public void cadastrar(@Valid @RequestBody ReceitaDto dados) {
        repository.save(new Receitas(dados, clienteRepository.findById(dados.clienteid())));
    }
}

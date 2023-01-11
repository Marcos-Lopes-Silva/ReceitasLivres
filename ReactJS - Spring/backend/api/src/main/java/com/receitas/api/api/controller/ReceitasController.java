package com.receitas.api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.receitas.api.api.dto.DadosCadastroReceitas;
import com.receitas.api.api.dto.DadosDetalhamentoReceita;
import com.receitas.api.api.entities.Receitas;
import com.receitas.api.api.entities.Usuario;
import com.receitas.api.api.repositories.ReceitasRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/receitas")
public class ReceitasController {

    @Autowired
    private ReceitasRepository receitasRepository;

    @Transactional
    @PostMapping("/criar")
    public ResponseEntity<DadosDetalhamentoReceita> criarReceita(@Valid @RequestBody DadosCadastroReceitas dados,
            HttpServletRequest request, UriComponentsBuilder builder) {
        System.out.println(request.getAttribute("usuario").toString());
        Receitas receita = new Receitas(dados);
        receita.setUsuario((Usuario) request.getAttribute("usuario"));
        System.out.println(receita.toString());
        receitasRepository.save(receita);

        var uri = builder.path("/u/{id}").buildAndExpand(receita.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoReceita(receita));
    }
}

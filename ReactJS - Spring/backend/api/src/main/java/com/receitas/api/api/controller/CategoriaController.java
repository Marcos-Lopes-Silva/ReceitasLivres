package com.receitas.api.api.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.receitas.api.api.dto.DadosCadastroCategoria;
import com.receitas.api.api.dto.DadosCategoria;
import com.receitas.api.api.entities.Categoria;
import com.receitas.api.api.repositories.CategoriaRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
    @PostMapping("/criar")
    public ResponseEntity<DadosCategoria> cadastrar(@Valid @RequestBody DadosCadastroCategoria dados,
            HttpServletRequest request, UriComponentsBuilder builder) {

        Categoria categoria = new Categoria(dados);

        categoriaRepository.save(categoria);

        URI uri = builder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosCategoria(categoria));
    }
    
    @GetMapping()
    public ResponseEntity<List<DadosCategoria>> listar() {

        var lista = categoriaRepository.findAll();

        List<DadosCategoria> dados = new ArrayList<>();
        for (Categoria categoria : lista) {
            dados.add(new DadosCategoria(categoria));
        }

        return ResponseEntity.ok(dados);
    }
}

package com.receitas.api.api.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.receitas.api.api.api.assembler.ReceitaAssembler;
import com.receitas.api.api.api.assembler.ReceitaDissembler;
import com.receitas.api.api.api.dto.DadosCadastroReceitas;
import com.receitas.api.api.api.dto.DadosDetalhamentoReceita;
import com.receitas.api.api.api.dto.DadosListagemReceitas;
import com.receitas.api.api.domain.entities.Receita;
import com.receitas.api.api.domain.repositories.ReceitasRepository;
import com.receitas.api.api.domain.services.ReceitaService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/receitas")
@AllArgsConstructor
public class ReceitasController {

    private final ReceitaService receitaService;
    private final ReceitaDissembler receitaDissembler;
    private final ReceitaAssembler receitaAssembler;
    private final ReceitasRepository receitasRepository;

    @PostMapping("/create")
    public ResponseEntity<DadosDetalhamentoReceita> create(@Valid @RequestBody DadosCadastroReceitas dados,
            HttpServletRequest request, UriComponentsBuilder builder) {

        Receita receita = receitaDissembler.toDomain(dados);

        var uri = builder.path("/u/{id}").buildAndExpand(receita.getId()).toUri();

        return ResponseEntity.created(uri).body(receitaAssembler.toDTO(receitaService.create(receita)));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemReceitas>> list(
            @PageableDefault(size = 10, sort = "titulo") Pageable paginacao) {

        Page<Receita> lista = receitasRepository.findAll(paginacao);

        var dados = lista.getContent();
        List<DadosListagemReceitas> dadosList = new ArrayList<>();
        for (Receita r : dados) {
            dadosList.add(new DadosListagemReceitas(r));
        }
        Page<DadosListagemReceitas> dadosPage = new PageImpl<>(dadosList, paginacao, 0);

        return ResponseEntity.ok(dadosPage);
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosDetalhamentoReceita> getById(@PathVariable Long id) {
        var receita = receitasRepository.getReferenceById(id);

        return ResponseEntity.ok().body(receitaAssembler.toDTO(receita));
    }

    @PutMapping("{id}")
    public ResponseEntity<DadosDetalhamentoReceita> edit(@PathVariable Long id,
            @RequestBody DadosCadastroReceitas dados) {
        Receita receita = receitaDissembler.toDomain(dados, id);
        return ResponseEntity.ok().body(receitaAssembler.toDTO(receitaService.update(receita)));
    }
}

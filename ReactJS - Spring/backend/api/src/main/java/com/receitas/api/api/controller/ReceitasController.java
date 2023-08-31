package com.receitas.api.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.receitas.api.api.dto.DadosCadastroReceitas;
import com.receitas.api.api.dto.DadosDetalhamentoReceita;
import com.receitas.api.api.dto.DadosListagemReceitas;
import com.receitas.api.api.entities.Categoria;
import com.receitas.api.api.entities.Receitas;
import com.receitas.api.api.entities.Usuario;
import com.receitas.api.api.repositories.CategoriaRepository;
import com.receitas.api.api.repositories.ReceitasRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/receitas")
public class ReceitasController {

    @Autowired
    private ReceitasRepository receitasRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
    @PostMapping("/criar")
    public ResponseEntity<DadosDetalhamentoReceita> criarReceita(@Valid @RequestBody DadosCadastroReceitas dados,
            HttpServletRequest request, UriComponentsBuilder builder) {

        Receitas receita = new Receitas(dados);

        receita.setUsuario((Usuario) request.getAttribute("usuario"));

        if (receita.getCategoria() == null) {
            String id = "1";
            Categoria cat = categoriaRepository.getReferenceById(Long.parseLong(id));
            receita.setCategoria(cat);
        }

        

        if (receita.getPhoto() == null) {
            receita.setPhoto(null);
        }

        receitasRepository.save(receita);

        var uri = builder.path("/u/{id}").buildAndExpand(receita.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoReceita(receita));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemReceitas>> listarReceitas(
            @PageableDefault(size = 10, sort = "titulo") Pageable paginacao) {

        Page<Receitas> lista = receitasRepository.findAll(paginacao);

        var dados = lista.getContent();
        List<DadosListagemReceitas> dadosList = new ArrayList<>();
        for (Receitas r : dados) {
            dadosList.add(new DadosListagemReceitas(r));
        }
        Page<DadosListagemReceitas> dadosPage = new PageImpl<>(dadosList, paginacao, 0);

        return ResponseEntity.ok(dadosPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoReceita> getReceita(@PathVariable Long id) {
        var receita = receitasRepository.getReferenceById(id);

        return ResponseEntity.ok().body(new DadosDetalhamentoReceita(receita));
    }
}

package com.receitas.api.api.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.receitas.api.api.api.assembler.UsuarioAssembler;
import com.receitas.api.api.api.assembler.UsuarioDissembler;
import com.receitas.api.api.api.dto.DadosCadastroUsuario;
import com.receitas.api.api.api.dto.DadosDetalhamentoUsuario;
import com.receitas.api.api.api.dto.DadosEditarUsuario;
import com.receitas.api.api.api.dto.DadosListagemUser;
import com.receitas.api.api.domain.entities.Usuario;
import com.receitas.api.api.domain.repositories.UsuarioRepository;
import com.receitas.api.api.domain.services.UsuarioService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioDissembler usuarioDissembler;
    private final UsuarioAssembler usuarioAssembler;
    private final UsuarioService usuarioService;

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> create(@Valid @RequestBody DadosCadastroUsuario dados,
            UriComponentsBuilder builder) {

        Usuario user = usuarioDissembler.toDomain(dados);

        Usuario userCreated = usuarioService.create(user);
        var uri = builder.path("/u/{id}").buildAndExpand(userCreated.getId()).toUri();

        return ResponseEntity.created(uri).body(usuarioAssembler.toDTO(userCreated));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DadosListagemUser>> listar(
            @PageableDefault(size = 10, sort = "nome") Pageable paginacao) {

        var lista = usuarioRepository.findAllByAtivoTrue(paginacao);

        return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> atualizar(@PathVariable Long id,
            @RequestBody @Valid DadosEditarUsuario dados) {
        Usuario usuario = usuarioDissembler.toDomain(dados, id);
        return ResponseEntity.ok().body(usuarioAssembler.toDTO(usuarioService.update(usuario)));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        var user = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(user);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosDetalhamentoUsuario> detalhar(@PathVariable Long id) {
        var user = usuarioRepository.getReferenceById(id);

        return ResponseEntity.ok().body(usuarioAssembler.toDTO(user));
    }
}

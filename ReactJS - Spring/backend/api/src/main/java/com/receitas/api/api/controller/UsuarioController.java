package com.receitas.api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.receitas.api.api.dto.DadosCadastroUsuario;
import com.receitas.api.api.dto.DadosDetalhamentoUsuario;
import com.receitas.api.api.dto.DadosEditarUsuario;
import com.receitas.api.api.dto.DadosListagemUser;
import com.receitas.api.api.entities.Usuario;
import com.receitas.api.api.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/u")
public class UsuarioController {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(@Valid @RequestBody DadosCadastroUsuario dados,
            UriComponentsBuilder builder) {
        Usuario user = new Usuario(dados);
        user.setSenha(encoder.encode(user.getSenha()));
        usuarioRepository.save(user);

        var uri = builder.path("/u/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(user));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DadosListagemUser>> listar(
            @PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        return null;
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> atualizar(@RequestBody @Valid DadosEditarUsuario dados) {
        Usuario user = usuarioRepository.getReferenceById(dados.id());
        user.attInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        var user = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(user);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUsuario> detalhar(@PathVariable Long id) {
        var user = usuarioRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(user));
    }
}

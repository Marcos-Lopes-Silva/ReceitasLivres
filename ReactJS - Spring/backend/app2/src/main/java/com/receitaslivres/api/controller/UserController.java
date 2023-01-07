package com.receitaslivres.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.receitaslivres.api.dto.DadosAtualizacaoUser;
import com.receitaslivres.api.dto.DadosAutenticacao;
import com.receitaslivres.api.dto.DadosCadastroUser;
import com.receitaslivres.api.dto.DadosCadastroUserTipo;
import com.receitaslivres.api.dto.DadosDetalhamentoUser;
import com.receitaslivres.api.dto.DadosListagemUser;
import com.receitaslivres.api.entities.TiposUser;
import com.receitaslivres.api.entities.User;
import com.receitaslivres.api.infra.security.TokenService;
import com.receitaslivres.api.repositories.PerfilRepository;
import com.receitaslivres.api.repositories.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UserRepository repository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity createUser(@Valid @RequestBody DadosCadastroUser user, UriComponentsBuilder builder) {
        TiposUser tipo;

        if (user.tipo() != null) {
            tipo = perfilRepository.findByNome(user.tipo());
        } else
            tipo = perfilRepository.findByNome("ROLE_USER");

        DadosCadastroUserTipo userdto = new DadosCadastroUserTipo(user, tipo);

        User newuser = new User(userdto);
        newuser.adicionarPerfil(tipo);
        repository.save(newuser);
        var uri = builder.path("/user/{id}").buildAndExpand(newuser.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUser(newuser));
    }

    @GetMapping("/users")
    public ResponseEntity<Page<DadosListagemUser>> listUsers(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemUser::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity attUsers(@RequestBody @Valid DadosAtualizacaoUser dados) {
        var user = repository.getReferenceById(dados.id());
        user.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoUser(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var user = repository.getReferenceById(id);

        user.excluir();

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity login(@RequestBody @Valid DadosAutenticacao dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok(tokenService.gerarToken((User) authentication.getPrincipal()));
    }

}

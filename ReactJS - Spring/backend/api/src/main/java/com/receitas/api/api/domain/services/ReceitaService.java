package com.receitas.api.api.domain.services;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.receitas.api.api.domain.entities.Receita;
import com.receitas.api.api.domain.repositories.ReceitasRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReceitaService {

    private final ReceitasRepository receitasRepository;

    private final StorageService storageService;

    @Transactional
    public Receita create(Receita receita) {
        Objects.requireNonNull(receita);

        validate(receita);

        receita.getPhoto().setTemp(false);

        return receitasRepository.save(receita);
    }

    private void validate(Receita receitas) {

        if (!storageService.fileExists(receitas.getPhoto()))
            throw new RuntimeException(String.format("File %s not found", receitas.getPhoto().getFileName()));

    }

    public Receita update(Receita receita) {
        Objects.requireNonNull(receita);

        validate(receita);

        Receita existReceita = receitasRepository.findById(receita.getId()).orElseThrow(EntityNotFoundException::new);

        if (!receita.getPhoto().equals(existReceita.getPhoto())) {
            receita.getPhoto().setTemp(false);
            this.storageService.softDelete(existReceita.getPhoto());
        }
        existReceita.update(receita);
        receitasRepository.save(existReceita);
        receitasRepository.flush();

        return existReceita;
    }
}

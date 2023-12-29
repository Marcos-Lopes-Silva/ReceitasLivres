package com.receitas.api.api.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroEndereco(
                @NotBlank String logradouro,
                @NotBlank String bairro,
                @NotBlank @Pattern(regexp = "\\d{5}\\W\\d{3}") String cep,
                @NotBlank String cidade,
                @NotBlank String uf,

                String numero,
                String complemento) {

}

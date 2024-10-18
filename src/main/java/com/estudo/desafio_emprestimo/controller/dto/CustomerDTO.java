package com.estudo.desafio_emprestimo.controller.dto;

public record CustomerDTO(
        String cpf,
        Integer age,
        String name,
        Double income,
        String UF) {
}

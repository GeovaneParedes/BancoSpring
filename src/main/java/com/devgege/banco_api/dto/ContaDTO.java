package com.devgege.banco_api.dto;

import com.devgege.banco_api.model.Conta;

public record ContaDTO(Long id, String titular, Double saldo) {
    public ContaDTO(Conta conta) {
        this(conta.getId(), conta.getTitular(), conta.getSaldo());
    }
}

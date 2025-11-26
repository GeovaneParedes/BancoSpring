package com.devgege.banco_api.repository;

import com.devgege.banco_api.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    // Só de estender JpaRepository, já ganhamos: save, findAll, findById, delete...
    
    // Método customizado mágico: O Spring lê o nome e cria o SQL "WHERE titular = ?"
    Conta findByTitular(String titular);
}

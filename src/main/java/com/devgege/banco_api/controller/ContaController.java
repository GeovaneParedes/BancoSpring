package com.devgege.banco_api.controller;

import com.devgege.banco_api.model.Conta;
import com.devgege.banco_api.dto.ContaDTO;
import com.devgege.banco_api.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaRepository repository;

    @GetMapping
    public List<ContaDTO> listar() {
        return repository.findAll().stream()
                .map(ContaDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ContaDTO criar(@RequestBody Conta conta) {
        return new ContaDTO(repository.save(conta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(conta -> ResponseEntity.ok(new ContaDTO(conta)))
                .orElse(ResponseEntity.notFound().build());
    }

    // ATUALIZAR (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<ContaDTO> atualizar(@PathVariable Long id, @RequestBody Conta dadosNovos) {
        return repository.findById(id)
                .map(contaExistente -> {
                    contaExistente.setTitular(dadosNovos.getTitular());
                    // Só atualiza o saldo se vier algo diferente de nulo
                    if (dadosNovos.getSaldo() != null) contaExistente.setSaldo(dadosNovos.getSaldo());
                    
                    repository.save(contaExistente);
                    return ResponseEntity.ok(new ContaDTO(contaExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETAR (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 (Sucesso sem conteúdo)
        }
        return ResponseEntity.notFound().build();
    }
}

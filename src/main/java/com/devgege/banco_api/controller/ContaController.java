package com.devgege.banco_api.controller;

import com.devgege.banco_api.model.Conta;
import com.devgege.banco_api.dto.ContaDTO;
import com.devgege.banco_api.repository.ContaRepository;
import com.devgege.banco_api.mensageria.Produtor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contas")
public class ContaController {

    // --- INJEÇÕES DE DEPENDÊNCIA (Ficam juntas no topo) ---
    @Autowired
    private ContaRepository repository;

    @Autowired
    private Produtor produtor; 

    // --- MÉTODOS ---

    @GetMapping
    public List<ContaDTO> listar() {
        return repository.findAll().stream()
                .map(ContaDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ContaDTO criar(@RequestBody Conta conta) {
        // 1. Salva no MySQL
        Conta contaSalva = repository.save(conta);
        
        // 2. Manda pro RabbitMQ (Assíncrono)
        produtor.enviarMensagem("Bem-vindo(a) ao BancoSpring, " + conta.getTitular() + "!");
        
        // 3. Retorna pro usuário
        return new ContaDTO(contaSalva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(conta -> ResponseEntity.ok(new ContaDTO(conta)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaDTO> atualizar(@PathVariable Long id, @RequestBody Conta dadosNovos) {
        return repository.findById(id)
                .map(contaExistente -> {
                    contaExistente.setTitular(dadosNovos.getTitular());
                    if (dadosNovos.getSaldo() != null) contaExistente.setSaldo(dadosNovos.getSaldo());
                    
                    repository.save(contaExistente);
                    return ResponseEntity.ok(new ContaDTO(contaExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

package com.devgege.banco_api.controller;

import com.devgege.banco_api.model.Conta;
import com.devgege.banco_api.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {

    @Autowired
    private ContaRepository repository;

    @GetMapping("/")
    public String paginaInicial(Model model) {
        model.addAttribute("listaDeContas", repository.findAll());
        return "contas"; 
    }

    @GetMapping("/nova")
    public String paginaCadastro(Model model) {
        model.addAttribute("conta", new Conta());
        return "formulario"; 
    }

    @PostMapping("/salvar")
    public String salvarConta(Conta conta) {
        repository.save(conta);
        return "redirect:/";
    }

    // --- MUDANÇA: AGORA TEMOS LOGIN ---

    // 1. Clicou em "Acessar", vem pra cá. Mostra tela de senha.
    @GetMapping("/login/{id}")
    public String paginaLogin(@PathVariable Long id, Model model) {
        model.addAttribute("idConta", id);
        return "login"; // Abre login.html
    }

    // 2. Digitou a senha, vem pra cá conferir.
    @PostMapping("/validar-acesso")
    public String validarSenha(@RequestParam Long id, @RequestParam String senha, Model model) {
        Conta conta = repository.findById(id).orElse(null);

        if (conta != null && conta.getSenha().equals(senha)) {
            // Senha bateu! Mostra a conta.
            model.addAttribute("conta", conta);
            return "detalhe"; 
        } else {
            // Senha errada! Volta pro login com erro.
            model.addAttribute("idConta", id);
            model.addAttribute("erro", true);
            return "login";
        }
    }

    // 3. Processa o dinheiro (Só acessível se já estiver na tela de detalhe)
    @PostMapping("/conta/{id}/operacao")
    public String realizarOperacao(@PathVariable Long id, 
                                   @RequestParam String tipo, 
                                   @RequestParam Double valor,
                                   Model model) { // Adicionamos Model pra retornar pra tela certa
        
        Conta conta = repository.findById(id).orElse(null);
        
        if (conta != null) {
            if ("DEPOSITO".equals(tipo)) {
                conta.setSaldo(conta.getSaldo() + valor);
            } else if ("SAQUE".equals(tipo)) {
                if (conta.getSaldo() >= valor) {
                    conta.setSaldo(conta.getSaldo() - valor);
                }
            }
            repository.save(conta);
            
            // Truque simples: Como não estamos usando Sessão de verdade (login persistente),
            // Vamos apenas renderizar a tela de detalhe novamente com os dados atualizados.
            model.addAttribute("conta", conta);
            return "detalhe";
        }
        return "redirect:/";
    }
}

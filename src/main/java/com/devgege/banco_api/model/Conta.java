package com.devgege.banco_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titular;
    private String senha;
    private Double saldo;
    
    // NOVO CAMPO
    private String departamento;

    public Conta() {}

    public Conta(String titular, String senha, Double saldo, String departamento) {
        this.titular = titular;
        this.senha = senha;
        this.saldo = saldo;
        this.departamento = departamento;
    }

    public Long getId() { return id; }
    public String getTitular() { return titular; }
    public void setTitular(String titular) { this.titular = titular; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public Double getSaldo() { return saldo; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }
    
    // Getters e Setters do novo campo
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
}

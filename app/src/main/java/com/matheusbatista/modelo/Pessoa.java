package com.matheusbatista.modelo;

/**
 * Created by matheus.batista on 04/04/2017.
 */

public class Pessoa {

    private final int id;
    private final String nome;
    private final String cpf;
    private final String telefone;

    public Pessoa(int id, String nome, String cpf, String telefone)
    {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public int getId()
    {
        return id;
    }
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }
}

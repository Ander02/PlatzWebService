/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.http.leitura;

import com.platz.model.UsuarioModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 15153770
 */
public class UsuarioLeitura {

    private String id;
    private String nome;
    private String cpf;
    private String imagemPerfil;
    private String telefone;
    private String dataNascimento;
    private String dataCadastro;
    private ContaLeitura conta;

    public UsuarioLeitura() {
    }

    public UsuarioLeitura(UsuarioModel model) {
        this.id = model.getId();
        this.nome = model.getNome();
        this.cpf = model.getCpf();
        this.imagemPerfil = model.getImagemPerfil();
        this.telefone = model.getTelefone();
        this.dataNascimento = model.getDataNascimento();
        this.dataCadastro = model.getDataCadatro();
        this.conta = new ContaLeitura(model.getConta());
    }

    public UsuarioLeitura(String id, String nome, String cpf, String imagemPerfil, String telefone, String dataNascimento, String dataCadastro, ContaLeitura conta) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.imagemPerfil = imagemPerfil;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
        this.conta = conta;
    }

    //Métodos
    public List<UsuarioLeitura> converterLista(List<UsuarioModel> modelList) {

        List<UsuarioLeitura> lista = new ArrayList<>();

        for (UsuarioModel model : modelList) {

            UsuarioLeitura usuario = new UsuarioLeitura(model);
            lista.add(usuario);
        }
        return lista;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public ContaLeitura getConta() {
        return conta;
    }

    public void setConta(ContaLeitura conta) {
        this.conta = conta;
    }

    public String getImagemPerfil() {
        return imagemPerfil;
    }

    public void setImagemPerfil(String imagemPerfil) {
        this.imagemPerfil = imagemPerfil;
    }

}

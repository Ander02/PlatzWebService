package com.platz.http.cadastro;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class UsuarioCadastro {

    private ContaCadastro conta;
    private String nome;
    private String dataNascimento;
    private String telefone;
    private String cpf;
    private String imagemPerfil;
    private EnderecoCadastro endereco;

    public UsuarioCadastro() {
    }

    public ContaCadastro getConta() {
        return conta;
    }

    public void setConta(ContaCadastro conta) {
        this.conta = conta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getImagemPerfil() {
        return imagemPerfil;
    }

    public void setImagemPerfil(String imagemPerfil) {
        this.imagemPerfil = imagemPerfil;
    }

    public EnderecoCadastro getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoCadastro endereco) {
        this.endereco = endereco;
    }

}

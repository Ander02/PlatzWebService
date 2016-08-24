package com.platz.http.edicao;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class UsuarioEdicao {

    private String nome;
    private String dataNascimento;
    private String telefone;
    private String cpf;
    private String imagemPerfil;

    public UsuarioEdicao() {
    }

    public UsuarioEdicao(String nome, String dataNascimento, String telefone, String cpf, String imagemPerfil) {
        setNome(nome);
        setDataNascimento(dataNascimento);
        setTelefone(telefone);
        setCpf(cpf);
        setImagemPerfil(imagemPerfil);
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

}
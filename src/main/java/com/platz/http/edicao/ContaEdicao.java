/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.http.edicao;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153766
 */
@XmlRootElement
public class ContaEdicao {
    
    private String nome;
    private String email;
    private String senha;
    private Date inativo;
    private Date bloqueado;
    private Date ultimoAcesso;

    public ContaEdicao() {
    }
        
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getInativo() {
        return inativo;
    }

    public void setInativo(Date inativo) {
        this.inativo = inativo;
    }

    public Date getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Date bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Date getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }
    
    
    
}

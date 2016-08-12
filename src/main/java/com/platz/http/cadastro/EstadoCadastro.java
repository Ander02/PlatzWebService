/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.http.cadastro;

import com.platz.model.EstadoModel;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class EstadoCadastro {

    private String nome;
    private String uf;

    public EstadoCadastro() {
    }

    public EstadoCadastro(String nome, String uf) {
        this.nome = nome;
        this.uf = uf;
    }

    public EstadoCadastro(EstadoModel model) {
        this.nome = model.getNome();
        this.uf = model.getUf();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

}

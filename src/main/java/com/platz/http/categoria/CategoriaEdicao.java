/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.http.categoria;

import com.platz.model.Categoria;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153766
 */
@XmlRootElement
public class CategoriaEdicao {

    private String nome;

    //Construtores
    public CategoriaEdicao() {
    }

    public CategoriaEdicao(String nome) {
        this.nome = nome;
    }

    public CategoriaEdicao(Categoria entity) {
        this.nome = entity.getNome();
    }

    //Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

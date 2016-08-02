/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.http.categoria;

import com.platz.model.CategoriaModel;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153766
 */
@XmlRootElement
public class CategoriaCadastro {

    private String nome;

    //Construtores
    public CategoriaCadastro() {
    }

    public CategoriaCadastro(String nome) {
        this.nome = nome;
    }

    public CategoriaCadastro(CategoriaModel model) {
        this.nome = model.getNome();
    }
    
    public CategoriaCadastro(CategoriaLeitura categoria) {

        this.nome = categoria.getNome();

    }

    //Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
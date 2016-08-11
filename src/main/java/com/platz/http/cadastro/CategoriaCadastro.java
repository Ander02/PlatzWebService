/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.http.cadastro;

import com.platz.http.leitura.CategoriaLeitura;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153766
 */
@XmlRootElement
public class CategoriaCadastro {

    private String nome;
    private String caminhoIcone;

    //Construtores
    public CategoriaCadastro() {
    }

    public CategoriaCadastro(CategoriaLeitura categoria) {
        this.nome = categoria.getNome();
        this.caminhoIcone = categoria.getCaminhoIcone();

    }

    //Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCaminhoIcone() {
        return caminhoIcone;
    }

    public void setCaminhoIcone(String caminhoIcone) {
        this.caminhoIcone = caminhoIcone;
    }
    
    

}
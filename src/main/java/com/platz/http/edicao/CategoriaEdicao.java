/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.http.edicao;

import com.platz.model.CategoriaModel;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153766
 */
@XmlRootElement
public class CategoriaEdicao {

    private String nome;
    private String caminhoIcone;
    private Date deletado;

    //Construtores
    public CategoriaEdicao() {
    }

    public CategoriaEdicao(String nome, String caminhoIcone, Date deletado) {
        setNome(nome);
        setCaminhoIcone(caminhoIcone);
        setDeletado(deletado);
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

    public Date getDeletado() {
        return deletado;
    }

    public void setDeletado(Date deletado) {
        this.deletado = deletado;
    }

}

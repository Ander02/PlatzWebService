package com.platz.http.cadastro;

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

    public CategoriaCadastro(String nome, String caminhoIcone) {
        setNome(nome);
        setCaminhoIcone(caminhoIcone);
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

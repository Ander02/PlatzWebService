package com.platz.http.assunto;

import com.platz.model.AssuntoModel;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class AssuntoEdicao {

    private String nome;

    //Construtores
    public AssuntoEdicao() {
    }

    public AssuntoEdicao(String nome) {
        this.nome = nome;
    }

    public AssuntoEdicao(AssuntoModel model) {
        this.nome = model.getNome();
    }

    //Getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
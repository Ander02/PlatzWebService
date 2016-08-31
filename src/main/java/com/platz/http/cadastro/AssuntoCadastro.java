package com.platz.http.cadastro;

import com.platz.model.AssuntoModel;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class AssuntoCadastro {

    private String nome;

    //Construtores
    public AssuntoCadastro() {
    }

    public AssuntoCadastro(String nome) {
        setNome(nome);
    }

    public AssuntoCadastro(AssuntoModel model) {
        setNome(model.getNome());
    }

    //Getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

package com.platz.http.edicao;

import com.platz.model.AssuntoModel;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class AssuntoEdicao {

    private String nome;
    private Date deletado;

    //Construtores
    public AssuntoEdicao() {
    }

    public AssuntoEdicao(AssuntoModel model) {
        this.nome = model.getNome();
        if (model.getDeletado() != null) {
            this.deletado = model.getDeletado();
        }
    }

    //Getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDeletado() {
        return deletado;
    }

    public void setDeletado(Date deletado) {
        this.deletado = deletado;
    }

}

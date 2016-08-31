package com.platz.http.edicao;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class AvaliacaoEdicao {

    private Integer nota;

    public AvaliacaoEdicao() {
    }

    public AvaliacaoEdicao(Integer nota) {
        setNota(nota);
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

}

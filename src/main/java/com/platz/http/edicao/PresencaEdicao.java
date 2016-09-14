package com.platz.http.edicao;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class PresencaEdicao {

    private Integer tipoPresenca;

    public PresencaEdicao() {
    }

    public PresencaEdicao(Integer tipoPresenca) {
        setTipoPresenca(tipoPresenca);
    }

    public Integer getTipoPresenca() {
        return tipoPresenca;
    }

    public void setTipoPresenca(Integer tipoPresenca) {
        this.tipoPresenca = tipoPresenca;
    }

}

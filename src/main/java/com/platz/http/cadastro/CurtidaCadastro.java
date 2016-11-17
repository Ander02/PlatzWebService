package com.platz.http.cadastro;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class CurtidaCadastro {

    private String eventoId;
    private String usuarioId;
    private boolean curtida;

    public CurtidaCadastro() {
    }

    public String getEventoId() {
        return eventoId;
    }

    public void setEventoId(String eventoId) {
        this.eventoId = eventoId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public boolean isCurtida() {
        return curtida;
    }

    public void setCurtida(boolean curtida) {
        this.curtida = curtida;
    }

}

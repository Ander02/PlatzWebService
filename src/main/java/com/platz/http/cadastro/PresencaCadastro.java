package com.platz.http.cadastro;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class PresencaCadastro {

    private String eventoId;
    private String contaId;
    private Integer tipoPresenca;

    public PresencaCadastro() {
    }

    public PresencaCadastro(String eventoId, String contaId, int tipoPresenca) {
        setEventoId(eventoId);
        setContaId(contaId);
        setTipoPresenca(tipoPresenca);
    }

    public String getEventoId() {
        return eventoId;
    }

    public void setEventoId(String eventoId) {
        this.eventoId = eventoId;
    }

    public String getContaId() {
        return contaId;
    }

    public void setContaId(String contaId) {
        this.contaId = contaId;
    }

    public Integer getTipoPresenca() {
        return tipoPresenca;
    }

    public void setTipoPresenca(Integer tipoPresenca) {
        this.tipoPresenca = tipoPresenca;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private int tipoPresenca;

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

    public int getTipoPresenca() {
        return tipoPresenca;
    }

    public void setTipoPresenca(int tipoPresenca) {
        this.tipoPresenca = tipoPresenca;
    }

}

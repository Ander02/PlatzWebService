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
public class AvaliacaoCadastro {
    private Integer nota;
    private String eventoId;
    private String usuarioId;

    public AvaliacaoCadastro() {
    }

    public AvaliacaoCadastro(Integer nota, String eventoId, String usuarioId) {
        setNota(nota);
        setEventoId(eventoId);
        setUsuarioId(usuarioId);
    }  
    
    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
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
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

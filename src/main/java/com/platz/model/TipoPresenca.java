/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.model;

/**
 *
 * @author 15153770
 */
public enum TipoPresenca {
    SIM("Participarei"),
    TALVEZ("Talvez participarei"),
    NAO("Não Participarei");
    
    private String label;

    private TipoPresenca(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

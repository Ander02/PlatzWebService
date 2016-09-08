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

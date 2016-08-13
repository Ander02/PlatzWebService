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
public enum Perfil {

    ADMINISTRADOR("Administrador"),
    EMPRESA("Empresa"),
    USUARIO("Usuario");

    private String label;

    private Perfil(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}

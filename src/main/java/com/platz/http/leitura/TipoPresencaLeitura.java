/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.http.leitura;

import com.platz.model.TipoPresenca;

/**
 *
 * @author 15153770
 */
public class TipoPresencaLeitura {

    private int codigo;
    private String nome;

    public TipoPresencaLeitura() {
    }

    public TipoPresencaLeitura(TipoPresenca tipoPresenca) {
        setCodigo(tipoPresenca.ordinal());
        setNome(tipoPresenca.getLabel());
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

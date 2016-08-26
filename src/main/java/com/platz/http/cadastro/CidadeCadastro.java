package com.platz.http.cadastro;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class CidadeCadastro {
    private String estadoId;
    private String nome;

    public CidadeCadastro() {
    }

    public CidadeCadastro(String estadoId, String nome) {
        setEstadoId(estadoId);
        setNome(nome);
    }    
    
    public String getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(String estadoId) {
        this.estadoId = estadoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.http.leitura;

import com.platz.model.EstadoModel;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */

@XmlRootElement
public class EstadoLeitura {

    private String id;
    private String nome;
    private String uf;
    private String dataCadastro;

    public EstadoLeitura() {
    }

    public EstadoLeitura(String id, String nome, String uf, String dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
        this.dataCadastro = dataCadastro;
    }

    public EstadoLeitura(EstadoModel model) {
        this.id = model.getId();
        this.nome = model.getNome();
        this.uf = model.getUf();
        this.dataCadastro = model.getDataCadatro();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    //Métodos

    public List<EstadoLeitura> converterLista(List<EstadoModel> modelList) {

        List<EstadoLeitura> lista = new ArrayList<>();

        for (EstadoModel model : modelList) {

            EstadoLeitura estado = new EstadoLeitura(model);
            lista.add(estado);
        }
        return lista;
    }
}

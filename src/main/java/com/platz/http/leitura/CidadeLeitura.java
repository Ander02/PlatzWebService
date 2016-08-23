/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.http.leitura;

import com.platz.model.CidadeModel;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class CidadeLeitura {
    private String id;
    private String nome;
    private EstadoLeitura estadoLeitura;
    private String dataCadastro;

    public CidadeLeitura() {
    }

    public CidadeLeitura(CidadeModel model) {
        setId(model.getId());
        setNome(model.getNome());
        setEstadoLeitura(new EstadoLeitura(model.getEstado()));
        setDataCadastro(model.getDataCadatro());
    }

    public CidadeLeitura(String id, String nome, EstadoLeitura estadoLeitura, String dataCadastro) {        
        setId(id);
        setNome(nome);
        setEstadoLeitura(estadoLeitura);
        setDataCadastro(dataCadastro);
    }

     //Métodos
    public List<CidadeLeitura> converterLista(List<CidadeModel> modelList) {

        List<CidadeLeitura> lista = new ArrayList<>();

        for (CidadeModel model : modelList) {

            CidadeLeitura cidade = new CidadeLeitura(model);
            lista.add(cidade);
        }
        return lista;
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

    public EstadoLeitura getEstadoLeitura() {
        return estadoLeitura;
    }

    public void setEstadoLeitura(EstadoLeitura estadoLeitura) {
        this.estadoLeitura = estadoLeitura;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    
}

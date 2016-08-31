/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.http.leitura;

import com.platz.model.EnderecoModel;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153766
 */
@XmlRootElement
public class EnderecoLeitura {

    private String cep;
    private String rua;
    private String bairro;
    private String numero;
    private String complemento;
    private CidadeLeitura cidade;

    public EnderecoLeitura() {
    }

    public EnderecoLeitura(EnderecoModel model) {
        setCep(model.getCep());
        setRua(model.getRua());
        setBairro(model.getBairro());
        setNumero(model.getNumero());
        setComplemento(model.getComplemento());
        setCidade(new CidadeLeitura(model.getCidade()));
    }

    //Getters and Setters
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public CidadeLeitura getCidade() {
        return cidade;
    }

    public void setCidade(CidadeLeitura cidade) {
        this.cidade = cidade;
    }

}

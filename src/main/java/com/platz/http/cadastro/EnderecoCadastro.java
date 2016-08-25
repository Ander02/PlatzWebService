package com.platz.http.cadastro;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class EnderecoCadastro {
    
    private String cep;
    private String rua;
    private String bairro;
    private String numero;
    private String complemento;
    private String cidadeId;
    
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
    
    public String getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(String cidadeId) {
        this.cidadeId = cidadeId;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.model;

import com.platz.util.DataUtil;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

/**
 *
 * @author 15153770
 */
public class EmpresaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;
    @NotNull(message = "O nome deve ser informado")
    private ContaModel conta;
    @CNPJ
    @NotNull(message = "O CNPJ deve ser informado")
    private String cnpj;
    @NotNull(message = "O nome fantasia deve ser informado")
    private String nomeFantasia;
    @NotNull(message = "a razao social deve ser informada")
    private String razaoSocial;
    @NotNull(message = "O telefone deve ser informado")
    @Length(min = 10, max = 11, message = "O telefone deve ter entre 10 e 11 caracteres")
    private String telefone;
    @Length(min = 10, max = 11, message = "O telefone deve ter entre 10 e 11 caracteres")
    private String telefone2;
    private String imagemPerfil;
    //Endereco endereco

    public EmpresaModel() {
    }

    public String getId() {
        return id.toHexString();
    }

    public void setId(String id) {
        this.id = new ObjectId(id);
    }

    public ObjectId getObjectId() {
        return this.id;
    }

    public ContaModel getConta() {
        return conta;
    }

    public void setConta(ContaModel conta) {
        this.conta = conta;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getImagemPerfil() {
        return imagemPerfil;
    }

    public void setImagemPerfil(String imagemPerfil) {
        this.imagemPerfil = imagemPerfil;
    }

    public String getDataCadatro() {
        return new DataUtil().converterData(id.getDate());
    }

}

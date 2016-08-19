/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.model;

import com.platz.http.cadastro.EstadoCadastro;
import com.platz.util.DataUtil;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author 15153770
 */
@Entity
@Table(name = "estado")
public class EstadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;
    @NotNull(message = "O nome do estado deve ser informado")
    private String nome;
    @NotNull(message = "A uf deve ser informado")
    @Length(min = 2, max = 2)
    private String uf;

    public EstadoModel() {
    }

    public EstadoModel(EstadoCadastro estado) {
        this.nome = estado.getNome();
        this.uf = estado.getUf();
    }

    //getters and setter
    public String getId() {
        return id.toHexString();
    }

    public void setId(String id) {
        this.id = new ObjectId(id);
    }

    public ObjectId getObjectId() {
        return this.id;
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

    public String getDataCadatro() {
        return new DataUtil().converterData(id.getDate());
    }

}

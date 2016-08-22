/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.model;

import com.platz.http.cadastro.UsuarioCadastro;
import com.platz.util.DataUtil;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author 15153770
 */
@Entity
@Table(name = "usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    @NotNull(message = "A conta deve ser informada")    
    private ContaModel conta;
    @NotNull(message = "O nome deve ser informado")
    @Length(min = 8, max = 64)    
    private String nome;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "A data de nascimento deve ser informada")
    private Date dataNascimento;    
    @Length(min = 10, max = 11)
    private String telefone;
    @CPF
    @NotNull(message = "O CPF deve ser informado")
    private String cpf;
    private String imagemPerfil;    
    //Endereco endereco

    public UsuarioModel() {
        
    }
    public UsuarioModel(UsuarioCadastro usuario) {
        this.conta = new ContaModel(usuario.getConta());
        this.cpf = usuario.getCpf();
        this.dataNascimento = new DataUtil().converterData(usuario.getDataNascimento());
        this.imagemPerfil = usuario.getImagemPerfil();
        this.telefone = usuario.getTelefone();
        this.nome = usuario.getNome();
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return new DataUtil().converterData(dataNascimento);
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

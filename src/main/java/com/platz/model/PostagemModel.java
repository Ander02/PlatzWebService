/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.model;

import com.platz.dao.ContaDao;
import com.platz.dao.EventoDao;
import com.platz.http.cadastro.PostagemCadastro;
import com.platz.util.DataUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author 15153770
 */
@Entity
@Table(name = "postagem")
public class PostagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    ObjectId id;

    @Length(max = 512, message = "O conteudo pode ter no maximo 512 caracteres")
    private String conteudo;
    @NotNull(message = "Indique a conta")
    @ManyToOne
    private ContaModel conta;
    @NotNull(message = "Indique o evento")
    @ManyToOne
    private EventoModel evento;
    @Temporal(TemporalType.TIMESTAMP)
    private Date censurado = null;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletado = null;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<ImagemModel> imagens = new ArrayList<>();

    public PostagemModel() {
    }

    public PostagemModel(PostagemCadastro postagem) {
        setConteudo(postagem.getConteudo());
        setConta(new ContaDao().buscarPorId(ContaModel.class, postagem.getContaId()));
        setEvento(new EventoDao().buscarPorId(EventoModel.class, postagem.getEventoId()));
    }

    //getters and setters
    public String getId() {
        return id.toHexString();
    }

    public void setId(String id) {
        this.id = new ObjectId(id);
    }

    public ObjectId getObjectId() {
        return this.id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public ContaModel getConta() {
        return conta;
    }

    public void setConta(ContaModel conta) {
        this.conta = conta;
    }

    public EventoModel getEvento() {
        return evento;
    }

    public void setEvento(EventoModel evento) {
        this.evento = evento;
    }

    public Date getCensuradoDate() {
        return censurado;
    }

    public String getCensurado() {
        return new DataUtil().converterData(this.censurado);
    }

    public void setCensurado(Date censurado) {
        this.censurado = censurado;
    }

    public Date getDeletadoDate() {
        return deletado;
    }

    public String getDeletado() {
        return new DataUtil().converterData(this.deletado);
    }

    public void setDeletado(Date deletado) {
        this.deletado = deletado;
    }

    public List<ImagemModel> getImagens() {
        return imagens;
    }

    public String getDataCadastro() {
        return new DataUtil().converterData(id.getDate());
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.model;

import com.platz.dao.EventoDao;
import com.platz.dao.UsuarioDao;
import com.platz.http.cadastro.AvaliacaoCadastro;
import com.platz.util.DataUtil;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;

/**
 *
 * @author 15153770
 */
@Entity
@Table(name = "avaliacao")
public class AvaliacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;
    @Max(5)
    @Min(1)
    private Integer nota = null;
    @ManyToOne
    @NotNull(message = "indique o evento que sera avaliado")
    private EventoModel evento;
    @ManyToOne
    @NotNull(message = "indique o usuario que esta avaliando")
    private UsuarioModel usuario;

    public AvaliacaoModel() {
    }

    public AvaliacaoModel(AvaliacaoCadastro avaliacao) {
        setEvento(new EventoDao().buscarPorId(EventoModel.class, avaliacao.getEventoId()));
        setUsuario(new UsuarioDao().buscarPorId(UsuarioModel.class, avaliacao.getUsuarioId()));
        setNota(avaliacao.getNota());
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

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public EventoModel getEvento() {
        return evento;
    }

    public void setEvento(EventoModel evento) {
        this.evento = evento;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public String getDataCadastro() {
        return new DataUtil().converterData(id.getDate());
    }

}

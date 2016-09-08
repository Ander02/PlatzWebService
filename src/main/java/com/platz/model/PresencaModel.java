/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.model;

import com.platz.dao.ContaDao;
import com.platz.dao.EventoDao;
import com.platz.http.cadastro.PresencaCadastro;
import com.platz.util.DataUtil;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;

/**
 *
 * @author 15153770
 */
@Entity
@Table(name = "presenca")
public class PresencaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;

    @ManyToOne
    @NotNull(message = "Selecione o evento no qual marcarar a presença")
    private EventoModel evento;
    @ManyToOne
    @NotNull(message = "Selecione a conta na qual marcarar a presença")
    private ContaModel conta;
    @NotNull(message = "Selecione o o tipo de presença")
    private TipoPresenca tipoPresenca;

    public PresencaModel() {
    }

    public PresencaModel(PresencaCadastro presenca) {
        setEvento(new EventoDao().buscarPorId(EventoModel.class, presenca.getEventoId()));
        setConta(new ContaDao().buscarPorId(ContaModel.class, presenca.getContaId()));
        setTipoPresenca(presenca.getTipoPresenca());
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

    public EventoModel getEvento() {
        return evento;
    }

    public void setEvento(EventoModel evento) {
        this.evento = evento;
    }

    public ContaModel getConta() {
        return conta;
    }

    public void setConta(ContaModel conta) {
        this.conta = conta;
    }

    public TipoPresenca getTipoPresenca() {
        return tipoPresenca;
    }

    public void setTipoPresenca(TipoPresenca tipoPresenca) {
        this.tipoPresenca = tipoPresenca;
    }

    public void setTipoPresenca(Integer tipoPresenca) {
        if (tipoPresenca != null) {
            switch (tipoPresenca) {
                case 0:
                    setTipoPresenca(TipoPresenca.SIM);
                    break;
                case 1:
                    setTipoPresenca(TipoPresenca.TALVEZ);
                    break;
                case 2:
                    setTipoPresenca(TipoPresenca.NAO);
                    break;
                default:
                    break;
            }
        }
    }

    public String getDataCadastro() {
        return new DataUtil().converterData(id.getDate());
    }

}

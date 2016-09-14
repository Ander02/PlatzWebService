package com.platz.model;

import com.platz.dao.EstadoDao;
import com.platz.http.cadastro.CidadeCadastro;
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
@Table(name = "cidade")
public class CidadeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;
    @NotNull(message = "O nome da cidade deve ser informada")
    private String nome;
    @NotNull(message = "O estado deve ser informado")
    @ManyToOne
    private EstadoModel estado;

    public CidadeModel() {
    }

    public CidadeModel(CidadeCadastro cidade) {
        setNome(cidade.getNome());
        setEstado(new EstadoDao().buscarPorId(EstadoModel.class, cidade.getEstadoId()));
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EstadoModel getEstado() {
        return estado;
    }

    public void setEstado(EstadoModel estado) {
        this.estado = estado;
    }

    public String getDataCadastro() {
        return new DataUtil().converterData(id.getDate());
    }

}

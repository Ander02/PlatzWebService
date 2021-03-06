package com.platz.model;

import com.platz.dao.EventoDao;
import com.platz.dao.UsuarioDao;
import com.platz.http.cadastro.CurtidaCadastro;
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
 * @author Anderson
 */
@Entity
@Table(name = "curtida")
public class CurtidaModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;
    
    private Boolean curtido = false;
    @ManyToOne
    @NotNull(message = "indique o evento que sera curtido")
    private EventoModel evento;
    @ManyToOne
    @NotNull(message = "indique o usuario que esta curtindo")
    private UsuarioModel usuario;
    
    public CurtidaModel() {
    }
    
    public CurtidaModel(CurtidaCadastro curtido) {
        setEvento(new EventoDao().buscarPorId(EventoModel.class, curtido.getEventoId()));
        setUsuario(new UsuarioDao().buscarPorId(UsuarioModel.class, curtido.getUsuarioId()));
        setCurtido(curtido.isCurtida());
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
    
    public UsuarioModel getUsuario() {
        return usuario;
    }
    
    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
    
    public String getDataCadastro() {
        return new DataUtil().converterData(id.getDate());
    }
    
    public Boolean getCurtido() {
        return curtido;
    }
    
    public void setCurtido(Boolean curtido) {
        this.curtido = curtido;
    }
    
}

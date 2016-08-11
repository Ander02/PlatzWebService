package com.platz.model;

import com.platz.util.DataUtil;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Anderson
 */
@Entity
@Table(name = "conta")
public class ContaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;

    @Email(message = "Email inválido")
    @NotNull(message = "O email não pode ser nulo")
    private String email;

    @NotNull(message = "A senha não pode ser nula")
    private String senha;

    @Temporal(TemporalType.TIMESTAMP)
    private Date inativo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date bloqueado;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcesso;

    //Construtores
    public ContaModel() {
    }

    //Getters and setters
    public String getId() {
        return id.toHexString();
    }

    public void setId(String id) {
        this.id = new ObjectId(id);
    }

    public ObjectId getObjectId() {
        return this.id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public String getDataCadatro() {
        return new DataUtil().converterData(id.getDate());
    }

    public Date getInativo() {
        return inativo;
    }

    public void setInativo(Date inativo) {
        this.inativo = inativo;
    }

    public Date getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Date bloqueado) {
        this.bloqueado = bloqueado;
    }

}

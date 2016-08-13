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

    @NotNull(message = "O perfil não pode ser nulo")
    private Perfil perfil;

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
        this.setId(new ObjectId(id));
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

    public String getUltimoAcesso() {
        if (this.ultimoAcesso != null) {
            return new DataUtil().converterData(this.ultimoAcesso);
        }
        return null;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public String getDataCadatro() {
        return new DataUtil().converterData(id.getDate());
    }

    public String getInativo() {
        if (this.inativo != null) {
            return new DataUtil().converterData(this.inativo);
        }
        return null;
    }

    public void setInativo(Date inativo) {
        this.inativo = inativo;
    }

    public String getBloqueado() {
        if (this.bloqueado != null) {
            return new DataUtil().converterData(this.bloqueado);
        }
        return null;
    }

    public void setBloqueado(Date bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        switch (perfil) {
            case 0:
                this.perfil = Perfil.ADMINISTRADOR;
                break;
            case 1:
                this.perfil = Perfil.EMPRESA;
                break;
            case 2:
                this.perfil = Perfil.USUARIO;
                break;
            default:
                this.perfil = Perfil.USUARIO;
                break;
        }    }

    public void setId(ObjectId id) {
        this.id = id;
    }

}

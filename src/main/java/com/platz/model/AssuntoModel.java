package com.platz.model;

import java.util.Date;
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
 * @author Anderson
 */
@Entity
@Table(name = "assunto")
public class AssuntoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;
    
    @Length(max = 30, message = "O nome deve ter no máximo 30 caracteres")
    @NotNull(message = "O nome não pode ser nulo")
    private String nome;

    //Contrutores
    public AssuntoModel() {
    }

    public AssuntoModel(ObjectId id, String nome) {
        this.id = id;
        this.nome = nome;
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

    public Date getDataCadatro() {
        return id.getDate();
    }

}

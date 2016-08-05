package com.platz.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.bson.types.ObjectId;

/**
 *
 * @author Anderson
 */

@Entity
@Table(name = "categoria")
public class CategoriaModel {
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;
    private String nome;

    //Contrutores
    public CategoriaModel() {
    }

    public CategoriaModel(String nome) {
        this.nome = nome;
    }      
    
    //get e setter
    public String getId() {
        return id.toHexString();
    }
    public void setId(String id){
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

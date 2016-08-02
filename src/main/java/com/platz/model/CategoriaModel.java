package com.platz.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.bson.types.ObjectId;

/**
 *
 * @author Anderson
 */

@Entity
public class CategoriaModel {
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;
    private String nome;

    public CategoriaModel() {
    }

    public CategoriaModel(String nome) {
        this.nome = nome;
    }      
    
    public String getId() {
        return id.toString(); 
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

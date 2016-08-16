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
  //  @NotNull(message = "O nome não pode ser nulo")
    private String nome;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletado; 

    //Contrutores
    public AssuntoModel() {
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

    public String getDataCadatro() {
        return new DataUtil().converterData(id.getDate());
    }
    
    public Date getDeletado() {
        return deletado;
    }

     public void setDeletado(Date deletado) {
        this.deletado = deletado;
    }

}

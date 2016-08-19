package com.platz.model;

import com.platz.http.cadastro.CategoriaCadastro;
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
import org.hibernate.validator.constraints.Length;

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

    @Length(max = 30, message = "O nome deve ter no máximo 30 caracteres")
    @NotNull(message = "O nome não pode ser nulo")
    private String nome;

    @NotNull(message = "Adicione um icone")
    @Length(max = 255, message = "O nome da imagem é muito longo")
    private String caminhoIcone;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deletado;

    //Contrutores
    public CategoriaModel() {
    }

    public CategoriaModel(CategoriaCadastro categoria) {
        this.nome = categoria.getNome();
        this.caminhoIcone = categoria.getCaminhoIcone();
    }

    public CategoriaModel(ObjectId id, String nome, String caminhoIcone, Date deletado) {
        this.id = id;
        this.nome = nome;
        this.caminhoIcone = caminhoIcone;
        this.deletado = deletado;
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

    public String getCaminhoIcone() {
        return caminhoIcone;
    }

    public void setCaminhoIcone(String caminhoIcone) {
        this.caminhoIcone = caminhoIcone;
    }

    public Date getDeletado() {
        return deletado;
    }

    public void setDeletado(Date deletado) {
        this.deletado = deletado;
    }

    public String getDataCadatro() {
        return new DataUtil().converterData(id.getDate());
    }
}

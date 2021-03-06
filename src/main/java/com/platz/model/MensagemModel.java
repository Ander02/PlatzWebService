package com.platz.model;

import com.platz.dao.AssuntoDao;
import com.platz.http.cadastro.MensagemCadastro;
import com.platz.util.DataUtil;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author 15153770
 */
@Entity
@Table(name = "mensagem")
public class MensagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;
    @ManyToOne
    @NotNull(message = "Assunto não pode ser nulo")
    private AssuntoModel assunto;
    @Email(message = "Email inválido")
    @Length(max = 50, message = "O email deve ter até 50 Caracteres")
    private String email;
    @Temporal(TemporalType.TIMESTAMP)
    private Date visualizado = null;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletado = null;
    private boolean marcado = false;
    @NotNull
    @Length(min = 8, max = 512, message = "A mensagem deve ter entre 8 e 4096 caracteres")
    private String conteudo;

    //Construtores
    public MensagemModel() {
    }

    public MensagemModel(MensagemCadastro mensagem) {
        setAssunto(new AssuntoDao().buscarPorId(AssuntoModel.class, mensagem.getAssuntoId()));
        setEmail(mensagem.getEmail());
        setConteudo(mensagem.getConteudo());
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

    public AssuntoModel getAssunto() {
        return assunto;
    }

    public void setAssunto(AssuntoModel assunto) {
        this.assunto = assunto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getVisualizadoDate() {
        return visualizado;
    }

    public String getVisualizado() {
        return new DataUtil().converterData(this.visualizado);
    }

    public void setVisualizado(Date visualizado) {
        this.visualizado = visualizado;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getDataCadastro() {
        return new DataUtil().converterData(id.getDate());
    }

    public Date getDeletadoDate() {
        return deletado;
    }

    public String getDeletado() {
        return new DataUtil().converterData(this.deletado);
    }

    public void setDeletado(Date deletado) {
        this.deletado = deletado;
    }

}

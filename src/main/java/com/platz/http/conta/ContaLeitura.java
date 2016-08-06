package com.platz.http.conta;

import com.platz.model.ContaModel;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class ContaLeitura {

    private String id;
    private String email;
    private Boolean ativo;
    private Boolean bloqueado;
    private Date ultimoAcesso;
    private Date dataCadastro;

    //Construtores
    public ContaLeitura() {
    }

    public ContaLeitura(String id, String email, Boolean ativo, Boolean bloqueado, Date ultimoAcesso, Date dataCadastro) {
        this.id = id;
        this.email = email;
        this.ativo = ativo;
        this.bloqueado = bloqueado;
        this.ultimoAcesso = ultimoAcesso;
        this.dataCadastro = dataCadastro;
    }

    public ContaLeitura(ContaModel model) {
        this.id = model.getId();
        this.email = model.getEmail();
        this.ativo = model.getAtivo();
        this.bloqueado = model.getBloqueado();
        this.ultimoAcesso = model.getUltimoAcesso();
        this.dataCadastro = model.getDataCadatro();

    }
    
    //Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Date getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    

}

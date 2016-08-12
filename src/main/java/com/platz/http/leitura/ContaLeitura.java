package com.platz.http.leitura;

import com.platz.model.ContaModel;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class ContaLeitura {

    private String id;
    private String email;
    private String inativo;
    private String perfil;
    private String bloqueado;
    private String ultimoAcesso;
    private String dataCadastro;

    //Construtores
    public ContaLeitura() {
    }

    public ContaLeitura(ContaModel model) {
        this.id = model.getId();
        this.email = model.getEmail();
        this.dataCadastro = model.getDataCadatro();
        this.perfil = model.getPerfil().getLabel();
        this.inativo = model.getInativo();
        this.bloqueado = model.getBloqueado();
        this.ultimoAcesso = model.getUltimoAcesso();
    }

    //Métodos
    public List<ContaLeitura> converterLista(List<ContaModel> modelList) {

        List<ContaLeitura> lista = new ArrayList<>();

        for (ContaModel model : modelList) {

            ContaLeitura conta = new ContaLeitura(model);
            lista.add(conta);
        }
        return lista;
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

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getInativo() {
        return inativo;
    }

    public void setInativo(String inativo) {
        this.inativo = inativo;
    }

    public String getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(String bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(String ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

}

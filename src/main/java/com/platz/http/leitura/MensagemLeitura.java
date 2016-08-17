package com.platz.http.leitura;

import com.platz.model.MensagemModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class MensagemLeitura {

    private String id;
    private String email;
    private String conteudo;
    private String visualizado;
    private boolean marcado;
    private String dataCadastro;
    private String deletado;
    private AssuntoLeitura assunto;

    public MensagemLeitura() {
    }

    public MensagemLeitura(MensagemModel model) {
        this.id = model.getId();
        this.email = model.getEmail();
        this.conteudo = model.getConteudo();
        this.marcado = model.isMarcado();
        this.dataCadastro = model.getDataCadatro();
        if (model.getVisualizado() != null) {
            this.visualizado = model.getVisualizado();
        }
        if (model.getDeletado() != null) {
            this.deletado = model.getDeletado();
        }
        this.assunto = new AssuntoLeitura(model.getAssunto());
    }
    
    //Métodos
    public List<MensagemLeitura> converterLista(List<MensagemModel> modelList) {

        List<MensagemLeitura> lista = new ArrayList<>();

        for (MensagemModel model : modelList) {
            MensagemLeitura mensagem = new MensagemLeitura(model);
            lista.add(mensagem);
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

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDeletado() {
        return deletado;
    }

    public void setDeletado(String deletado) {
        this.deletado = deletado;
    }

    public AssuntoLeitura getAssunto() {
        return assunto;
    }

    public void setAssunto(AssuntoLeitura assunto) {
        this.assunto = assunto;
    }

    public String getVisualizado() {
        return visualizado;
    }

    public void setVisualizado(String visualizado) {
        this.visualizado = visualizado;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

}

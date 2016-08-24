package com.platz.http.leitura;

import com.platz.model.AssuntoModel;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class AssuntoLeitura {

    private String id;
    private String nome;
    private String dataCadastro;
    private String deletado;

    //Construtores
    public AssuntoLeitura() {
    }

    public AssuntoLeitura(String id, String nome, String dataCadastro, String deletado) {
        setId(id);
        setNome(nome);
        setDataCadastro(dataCadastro);
        setDeletado(deletado);
    }

    public AssuntoLeitura(AssuntoModel model) {
        setId(model.getId());
        setNome(model.getNome());
        setDataCadastro(model.getDataCadatro());
        if (model.getDeletado() != null) {
            setDeletado(model.getDeletado());
        }
    }

    //Métodos
    public List<AssuntoLeitura> converterLista(List<AssuntoModel> modelList) {

        List<AssuntoLeitura> lista = new ArrayList<>();

        for (AssuntoModel model : modelList) {

            AssuntoLeitura assunto = new AssuntoLeitura(model);
            lista.add(assunto);
        }
        return lista;
    }

    //Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

}

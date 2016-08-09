package com.platz.http.assunto;

import com.platz.model.AssuntoModel;
import java.util.ArrayList;
import java.util.Date;
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

    //Construtores
    public AssuntoLeitura() {
    }

    public AssuntoLeitura(String id, String nome, String dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.dataCadastro = dataCadastro;
    }

    public AssuntoLeitura(AssuntoModel model) {
        this.id = model.getId();
        this.nome = model.getNome();
        this.dataCadastro = model.getDataCadatro();
    }

    //Métodos
    public List<AssuntoLeitura> converterLista(List<AssuntoModel> modelList) {

        List<AssuntoLeitura> lista = new ArrayList<>();

        for (AssuntoModel model : modelList) {

            AssuntoLeitura categoria = new AssuntoLeitura(model);
            lista.add(categoria);
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

}

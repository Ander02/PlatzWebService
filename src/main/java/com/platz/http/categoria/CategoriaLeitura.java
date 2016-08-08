package com.platz.http.categoria;

import com.platz.model.CategoriaModel;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class CategoriaLeitura {

    private String id;
    private String nome;
    private String dataCadastro;

    //Construtores
    public CategoriaLeitura() {

    }

    public CategoriaLeitura(CategoriaModel model) {
        this.id = model.getId();
        this.nome = model.getNome();
        this.dataCadastro = model.getDataCadatro();
    }

    //Métodos
    public List<CategoriaLeitura> converterLista(List<CategoriaModel> modelList) {

        List<CategoriaLeitura> lista = new ArrayList<>();

        for (CategoriaModel model : modelList) {

            CategoriaLeitura categoria = new CategoriaLeitura(model);
            lista.add(categoria);
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

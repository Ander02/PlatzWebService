package com.platz.http.leitura;

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
    private String caminhoIcone;
    private String deletado;

    //Construtores
    public CategoriaLeitura() {

    }

    public CategoriaLeitura(String id, String nome, String dataCadastro, String caminhoIcone, String deletado) {
        setId(id);
        setNome(nome);
        setDataCadastro(dataCadastro);
        setCaminhoIcone(caminhoIcone);
        setDeletado(deletado);
    }

    public CategoriaLeitura(CategoriaModel model) {
        setId(model.getId());
        setNome(model.getNome());
        setDataCadastro(model.getDataCadastro());
        setCaminhoIcone(model.getCaminhoIcone());
        if (model.getDeletado() != null) {
            setDeletado(model.getDeletado());
        }
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

    public String getCaminhoIcone() {
        return caminhoIcone;
    }

    public void setCaminhoIcone(String caminhoIcone) {
        this.caminhoIcone = caminhoIcone;
    }

    public String getDeletado() {
        return deletado;
    }

    public void setDeletado(String deletado) {
        this.deletado = deletado;
    }

}

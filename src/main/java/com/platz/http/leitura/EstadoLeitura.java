package com.platz.http.leitura;

import com.platz.model.EstadoModel;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class EstadoLeitura {

    private String id;
    private String nome;
    private String uf;
    private String dataCadastro;

    public EstadoLeitura() {
    }

    public EstadoLeitura(EstadoModel model) {
        setId(model.getId());
        setNome(model.getNome());
        setUf(model.getUf());
        setDataCadastro(model.getDataCadastro());
    }

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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    //Métodos

    public List<EstadoLeitura> converterLista(List<EstadoModel> modelList) {

        List<EstadoLeitura> lista = new ArrayList<>();

        for (EstadoModel model : modelList) {

            EstadoLeitura estado = new EstadoLeitura(model);
            lista.add(estado);
        }
        return lista;
    }
}

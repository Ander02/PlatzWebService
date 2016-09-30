package com.platz.http.leitura;

import com.platz.model.CidadeModel;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class CidadeLeitura {

    private String id;
    private String nome;
    private EstadoLeitura estado;
    private String dataCadastro;

    public CidadeLeitura() {
    }

    public CidadeLeitura(CidadeModel model) {
        setId(model.getId());
        setNome(model.getNome());
        setEstado(new EstadoLeitura(model.getEstado()));
        setDataCadastro(model.getDataCadastro());
    }

    public CidadeLeitura(String id, String nome, EstadoLeitura estadoLeitura, String dataCadastro) {
        setId(id);
        setNome(nome);
        setEstado(estadoLeitura);
        setDataCadastro(dataCadastro);
    }

    //Métodos
    public List<CidadeLeitura> converterLista(List<CidadeModel> modelList) {

        List<CidadeLeitura> lista = new ArrayList<>();

        for (CidadeModel model : modelList) {

            CidadeLeitura cidade = new CidadeLeitura(model);
            lista.add(cidade);
        }
        return lista;
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

    public EstadoLeitura getEstado() {
        return estado;
    }

    public void setEstado(EstadoLeitura estadoLeitura) {
        this.estado = estadoLeitura;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}

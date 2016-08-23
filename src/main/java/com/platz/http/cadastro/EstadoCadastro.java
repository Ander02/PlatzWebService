package com.platz.http.cadastro;

import com.platz.model.EstadoModel;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class EstadoCadastro {

    private String nome;
    private String uf;

    public EstadoCadastro() {
    }

    public EstadoCadastro(String nome, String uf) {
        setNome(nome);
        setUf(uf);
    }

    public EstadoCadastro(EstadoModel model) {
        setNome(model.getNome());
        setUf(model.getUf());
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

}

package com.platz.http.leitura;

import com.platz.model.Perfil;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class PerfilLeitura {

    private int codigo;
    private String nome;

    //Construtores
    public PerfilLeitura() {
    }

    public PerfilLeitura(Perfil perfil) {
        setCodigo(perfil.ordinal());
        setNome(perfil.getLabel());
    }

    //Métodos
    public List<PerfilLeitura> converterLista(List<Perfil> enumList) {

        List<PerfilLeitura> lista = new ArrayList<>();

        for (Perfil perfil : enumList) {

            PerfilLeitura perfilLeitura = new PerfilLeitura(perfil);
            lista.add(perfilLeitura);
        }
        return lista;
    }

    //Getters and Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

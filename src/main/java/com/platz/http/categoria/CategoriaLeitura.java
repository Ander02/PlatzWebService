package com.platz.http.categoria;

import com.platz.model.Categoria;
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

    //Construtores
    public CategoriaLeitura() {

    }

    public CategoriaLeitura(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaLeitura(Categoria categoriaEntity) {
        this.id = categoriaEntity.getId();
        this.nome = categoriaEntity.getNome();
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

    //Métodos
    public List<CategoriaLeitura> converterLista(List<Categoria> entityList) {

        List<CategoriaLeitura> lista = new ArrayList<>();

        for (Categoria entity : entityList) {

            CategoriaLeitura categoria = new CategoriaLeitura(entity);
            lista.add(categoria);
        }
        return lista;
    }

}

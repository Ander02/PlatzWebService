package com.platz.util;

import com.platz.dao.EventoDao;
import com.platz.http.cadastro.CategoriaCadastro;
import com.platz.model.CategoriaModel;

/**
 *
 * @author Anderson
 */
public class Teste {

    public static void main(String[] args) {
        CategoriaModel c = new CategoriaModel(new CategoriaCadastro("teste", "url qualquer"));
        
        EventoDao e = new EventoDao();
        e.buscarPelaCategoria(c);
    }
}

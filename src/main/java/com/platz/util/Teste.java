package com.platz.util;

import com.platz.dao.CategoriaDao;
import com.platz.dao.EventoDao;
import com.platz.model.CategoriaModel;
import com.platz.model.EventoModel;
import java.util.List;

/**
 *
 * @author Anderson
 */
public class Teste {

    public static void main(String[] args) {
        CategoriaModel cat = new CategoriaDao().buscarPorId(CategoriaModel.class, "57c8805c05b3981224216f45");

        List<EventoModel> lista = new EventoDao().buscarPelaCategoria(cat);

        for (EventoModel eventoModel : lista) {
            System.out.println(cat.getNome());
            System.out.println(eventoModel.getNome());
        }
    }
}

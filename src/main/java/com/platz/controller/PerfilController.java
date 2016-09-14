package com.platz.controller;

import com.platz.model.Perfil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Anderson
 */
public class PerfilController {

    public List<Perfil> listarTodos() {

        List<Perfil> lista = new ArrayList<>();
        //Adicionar Todos os Perfis à lista
        lista.addAll(Arrays.asList(Perfil.values()));

        return lista;
    }

}

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

        //Criar Array List
        List<Perfil> lista = new ArrayList<>();

        //Adicionar Todos os Perfis à lista
        lista.addAll(Arrays.asList(Perfil.values()));

        //Retornar lista
        return lista;
    }

}

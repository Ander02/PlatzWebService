/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.controller;

import com.platz.model.TipoPresenca;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author 15153770
 */
public class TipoPresencaController {
    public List<TipoPresenca> listarTodos() {

        //Criar Array List
        List<TipoPresenca> lista = new ArrayList<>();

        //Adicionar Todos os Perfis à lista
        lista.addAll(Arrays.asList(TipoPresenca.values()));

        //Retornar lista
        return lista;
    }
}

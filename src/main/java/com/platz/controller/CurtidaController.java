/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.controller;

import com.platz.dao.CurtidaDao;
import com.platz.model.CurtidaModel;
import com.platz.model.EventoModel;
import com.platz.model.UsuarioModel;
import java.util.List;

/**
 *
 * @author 15153770
 */
public class CurtidaController {

    private final CurtidaDao curtidosDao = new CurtidaDao();

    public void curtir(CurtidaModel model) {
        model.setCurtido(true);
        curtidosDao.cadastrar(model);
    }

    public List<CurtidaModel> listarTodos() {
        return curtidosDao.listarTodos(CurtidaModel.class);
    }

    public CurtidaModel buscarPorId(String id) {
        return curtidosDao.buscarPorId(CurtidaModel.class, id);
    }

    public List<CurtidaModel> buscarPeloEvento(EventoModel evento) {
        return curtidosDao.buscarPorEvento(evento);
    }

    public List<CurtidaModel> buscarPeloUsuario(UsuarioModel usuario) {
        return curtidosDao.buscarPorUsuario(usuario);
    }

    public void descurtir(CurtidaModel model) {
        curtidosDao.excluir(model);
    }

}

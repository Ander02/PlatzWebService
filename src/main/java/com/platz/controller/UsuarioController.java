/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.controller;

import com.platz.dao.UsuarioDao;
import com.platz.model.ContaModel;
import com.platz.model.UsuarioModel;
import java.util.List;

/**
 *
 * @author 15153770
 */
public class UsuarioController {

    private final UsuarioDao usuarioDao = new UsuarioDao();

    public void cadastrar(UsuarioModel model) {
        usuarioDao.cadastrar(model);
    }

    public List<UsuarioModel> listarTodos() {
        return usuarioDao.listarTodos(UsuarioModel.class);
    }

    public UsuarioModel buscarPorId(String id) {
        return usuarioDao.buscarPorId(UsuarioModel.class, id);
    }

    public void alterar(UsuarioModel model) {
        usuarioDao.alterar(model);
    }

    public UsuarioModel buscarPeloCPF(String cpf) {
        return usuarioDao.buscarPeloCPF(cpf);
    }

    public List<UsuarioModel> buscarPeloNome(String nome) {
        return usuarioDao.bucarPeloNome(nome);
    }

    public UsuarioModel buscarPelaConta(ContaModel model) {
        return usuarioDao.buscarPelaConta(model);
    }
}

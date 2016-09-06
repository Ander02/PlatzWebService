/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.controller;

import com.platz.dao.AvaliacaoDao;
import com.platz.http.edicao.AvaliacaoEdicao;
import com.platz.model.AvaliacaoModel;
import com.platz.model.EventoModel;
import com.platz.model.UsuarioModel;
import java.util.List;

/**
 *
 * @author 15153770
 */
public class AvaliacaoController {
    
    private final AvaliacaoDao avaliacaoDao = new AvaliacaoDao();
    
    public void cadastrar(AvaliacaoModel model) {
        avaliacaoDao.cadastrar(model);
    }
    
    public List<AvaliacaoModel> listarTodos() {
        return avaliacaoDao.listarTodos(AvaliacaoModel.class);
    }
    
    public AvaliacaoModel buscarPorId(String id) {
        return avaliacaoDao.buscarPorId(AvaliacaoModel.class, id);
    }
    
    public List<AvaliacaoModel> buscarPeloEvento(EventoModel evento) {
        return avaliacaoDao.buscarPorEvento(evento);
    }
    
    public List<AvaliacaoModel> buscarPeloUsuario(UsuarioModel usuario) {
        return avaliacaoDao.buscarPorUsuario(usuario);
    }
    
    public void alterar(AvaliacaoModel model, AvaliacaoEdicao avaliacao) {
        if (avaliacao.getNota() != null && !avaliacao.getNota().equals("")) {
            model.setNota(avaliacao.getNota());
            avaliacaoDao.alterar(model);
        }
    }
    
    public void excluir(AvaliacaoModel model) {
        avaliacaoDao.excluir(model);
    }
    
}

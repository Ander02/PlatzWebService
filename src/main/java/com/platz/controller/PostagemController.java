/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.controller;

import com.platz.dao.PostagemDao;
import com.platz.http.edicao.PostagemEdicao;
import com.platz.model.EventoModel;
import com.platz.model.PostagemModel;
import com.platz.model.UsuarioModel;
import java.util.List;

/**
 *
 * @author 15153770
 */
public class PostagemController {
    
    private final PostagemDao postagemDao = new PostagemDao();
    
    public void cadastrar(PostagemModel model) {
        postagemDao.cadastrar(model);
    }
    
    public List<PostagemModel> listarTodos() {
        return postagemDao.listarTodos(PostagemModel.class);
    }
    
    public PostagemModel buscarPorId(String id) {
        return postagemDao.buscarPorId(PostagemModel.class, id);
    }
    
    public void alterar(PostagemModel model, PostagemEdicao postagem) {
        if (postagem.getConteudo() != null && !postagem.getConteudo().equals("")) {
            model.setConteudo(postagem.getConteudo());
            postagemDao.alterar(model);
        }
    }
    
    public void excluir(PostagemModel model) {
        postagemDao.excluir(model);
    }
    
    public List<PostagemModel> buscarPeloEvento(EventoModel evento) {
        return postagemDao.buscarPorEvento(evento);
    }
    
    public List<PostagemModel> buscarPeloUsuario(UsuarioModel usuario) {
        return postagemDao.buscarPorUsuario(usuario);
    }
}

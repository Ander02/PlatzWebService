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

    public Double mediaPorEvento(EventoModel evento) {
        List<AvaliacaoModel> models = avaliacaoDao.buscarPorEvento(evento);
        double total = 0.0;
        for (AvaliacaoModel model : models) {
            total += model.getNota();
        }
        if(total==0){
            return 0.0;
        }
        return total / models.size();
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
        if (avaliacao.getNota() != null) {
            model.setNota(avaliacao.getNota());
            avaliacaoDao.alterar(model);
        }
    }

    public void excluir(AvaliacaoModel model) {
        avaliacaoDao.excluir(model);
    }

}

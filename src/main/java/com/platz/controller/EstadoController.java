package com.platz.controller;

import com.platz.dao.EstadoDao;
import com.platz.model.EstadoModel;
import java.util.List;

/**
 *
 * @author 15153770
 */
public class EstadoController {

    private final EstadoDao estadoDao = new EstadoDao();

    public void cadastrar(EstadoModel model) {
        estadoDao.cadastrar(model);
    }

    public List<EstadoModel> listarTodos() {
        return estadoDao.listarTodos(EstadoModel.class);
    }

    public EstadoModel buscarPorId(String id) {
        return estadoDao.buscarPorId(EstadoModel.class, id);
    }

    public EstadoModel buscarPelaUf(String uf) {
        return estadoDao.buscarPelaUf(uf);
    }

    public List<EstadoModel> buscarPeloNome(String nome) {
        return estadoDao.buscarPeloNome(nome);
    }
}

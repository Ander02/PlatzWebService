package com.platz.controller;

import com.platz.dao.CidadeDao;
import com.platz.model.CidadeModel;
import com.platz.model.EstadoModel;
import java.util.List;

/**
 *
 * @author Anderson
 */
public class CidadeController {

    private final CidadeDao cidadeDao = new CidadeDao();

    public void cadastrar(CidadeModel model) {
        cidadeDao.cadastrar(model);
    }

    public List<CidadeModel> listarTodos() {
        return cidadeDao.listarTodos(CidadeModel.class);
    }

    public CidadeModel buscarPorId(String id) {
        return cidadeDao.buscarPorId(CidadeModel.class, id);
    }

    public List<CidadeModel> buscarPeloEstado(EstadoModel estado) {
        return cidadeDao.buscarPorEstado(estado);
    }

}

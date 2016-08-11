package com.platz.controller;

import com.platz.dao.AssuntoDao;
import com.platz.model.AssuntoModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Anderson
 */
public class AssuntoController {

    private final AssuntoDao assuntoDao = new AssuntoDao();

    public void cadastrar(AssuntoModel model) {
        assuntoDao.cadastrar(model);
    }

    public List<AssuntoModel> listarTodos() {
        return assuntoDao.listarTodos(AssuntoModel.class);
    }

    public AssuntoModel buscarPorId(String id) {
        return assuntoDao.buscarPorId(AssuntoModel.class, id);
    }

    public List<AssuntoModel> buscarPeloNome(String nome) {
        return assuntoDao.buscarPeloNome(nome);
    }

    public void alterar(AssuntoModel model) {
        assuntoDao.alterar(model);
    }

    public void excluir(AssuntoModel model) {
        model.setDeletado(new Date());
        assuntoDao.alterar(model);
    }

}

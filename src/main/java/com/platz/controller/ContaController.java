package com.platz.controller;

import com.platz.dao.ContaDao;
import com.platz.model.ContaModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Anderson
 */
public class ContaController {

    private final ContaDao contaDao = new ContaDao();

    public void cadastrar(ContaModel model) {
        contaDao.cadastrar(model);
    }

    public List<ContaModel> listarTodos() {
        return contaDao.listarTodos(ContaModel.class);
    }

    public ContaModel buscarPorId(String id) {
        return contaDao.buscarPorId(ContaModel.class, id);
    }

    public ContaModel buscarPeloEmail(String email) {
        return contaDao.buscarPeloEmail(email);
    }

    public void inverterAtividade(ContaModel model) {

        if (model.getInativo() == null) {
            model.setInativo(new Date());
        } else {
            model.setInativo(null);
        }
        contaDao.alterar(model);
    }

    public List<ContaModel> buscarAtivos() {
        return contaDao.buscarAtivos();
    }

    public List<ContaModel> buscarInativos() {
        return contaDao.buscarInativos();
    }

    public void alterar(ContaModel model) {
        contaDao.alterar(model);
    }

}

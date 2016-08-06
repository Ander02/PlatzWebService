package com.platz.controller;

import com.platz.dao.ContaDao;
import com.platz.model.ContaModel;
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

    public List<ContaModel> buscarPeloEmail(String email) {
        return contaDao.buscarPeloEmail(email);
    }

    public List<ContaModel> buscarPelaAtividade(Boolean atividade) {
        return contaDao.buscarPelaAtividade(atividade);
    }

    public void inverterAtividade(ContaModel model) {
        model.setAtivo(!model.getAtivo());
        contaDao.alterar(model);
    }
    
    public void bloquear(ContaModel model){
        model.setBloqueado(true);
        contaDao.alterar(model);
    }
    
    public void alterar(ContaModel model){
        contaDao.alterar(model);
    }
    
    

}

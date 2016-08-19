package com.platz.controller;

import com.platz.dao.EmpresaDao;
import com.platz.model.ContaModel;
import com.platz.model.EmpresaModel;
import java.util.List;

/**
 *
 * @author Anderson
 */
public class EmpresaController {

    private final EmpresaDao empresaDao = new EmpresaDao();

    public void cadastrar(EmpresaModel model) {
        empresaDao.cadastrar(model);
    }

    public List<EmpresaModel> listarTodos() {
        return empresaDao.listarTodos(EmpresaModel.class);
    }

    public List<EmpresaModel> buscarPeloNome(String nome) {
        return empresaDao.bucarPeloNome(nome);
    }

    public EmpresaModel buscarPorId(String id) {
        return empresaDao.buscarPorId(EmpresaModel.class, id);
    }

    public EmpresaModel buscarPeloCNPJ(String cnpj) {
        return empresaDao.bucarPeloCNPJ(cnpj);
    }

    public EmpresaModel buscarPelaConta(ContaModel conta) {
        return empresaDao.buscarPelaConta(conta);
    }

    public void alterar(EmpresaModel model) {
        empresaDao.alterar(model);
    }

}

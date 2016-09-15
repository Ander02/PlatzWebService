package com.platz.controller;

import com.platz.dao.EmpresaDao;
import com.platz.http.edicao.EmpresaEdicao;
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

    public void alterar(EmpresaModel model, EmpresaEdicao empresa) {

        if (empresa.getCnpj() != null && !empresa.getCnpj().equals("")) {
            model.setCnpj(empresa.getCnpj());
        }
        if (empresa.getImagemPerfil() != null && !empresa.getImagemPerfil().equals("")) {
            model.setImagemPerfil(empresa.getImagemPerfil());
        }
        if (empresa.getNomeFantasia() != null && !empresa.getNomeFantasia().equals("")) {
            model.setNomeFantasia(empresa.getNomeFantasia());
        }
        if (empresa.getRazaoSocial() != null && !empresa.getRazaoSocial().equals("")) {
            model.setRazaoSocial(empresa.getRazaoSocial());
        }
        if (empresa.getTelefone() != null && !empresa.getTelefone().equals("")) {
            model.setTelefone(empresa.getTelefone());
        }
        if (empresa.getTelefone2() != null && !empresa.getTelefone2().equals("")) {
            model.setTelefone2(empresa.getTelefone2());
        }
        empresaDao.alterar(model);
    }

}

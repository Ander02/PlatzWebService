package com.platz.controller;

import com.platz.dao.CategoriaDao;
import com.platz.model.CategoriaModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Anderson
 */
public class CategoriaController {

    private final CategoriaDao categoriaDao = new CategoriaDao();

    public void cadastrar(CategoriaModel model) {
        categoriaDao.cadastrar(model);
    }

    public List<CategoriaModel> listarTodos() {
        return categoriaDao.listarTodos(CategoriaModel.class);
    }

    public CategoriaModel buscarPorId(String id) {
        return categoriaDao.buscarPorId(CategoriaModel.class, id);
    }

    public List<CategoriaModel> buscarPeloNome(String nome) {
        return categoriaDao.buscarPeloNome(nome);
    }

    public void alterar(CategoriaModel model) {
        categoriaDao.alterar(model);
    }

    public void excluir(CategoriaModel model) {
        model.setDeletado(new Date());
        categoriaDao.alterar(model);
    }
    

}

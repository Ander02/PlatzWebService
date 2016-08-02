package com.platz.controller;

import com.platz.dao.CategoriaDao;
import com.platz.model.Categoria;
import java.util.List;

/**
 *
 * @author Anderson
 */
public class CategoriaController {

    private final CategoriaDao categoriaDao = new CategoriaDao();

    public void cadastrar(Categoria entity) {
        categoriaDao.cadastrar(entity);
    }

    public List<Categoria> listarTodos() {
        return categoriaDao.listarTodos(Categoria.class);
    }

    public Categoria buscarPorId(Integer id) {
        return categoriaDao.buscarPorId(Categoria.class, id);
    }

    public List<Categoria> buscarPeloNome(String nome) {
        return categoriaDao.buscarPeloNome(nome);
    }

    public void alterar(Categoria entity) {
        categoriaDao.alterar(entity);
    }

    public void excluir(Categoria entity) {
        categoriaDao.excluir(entity);
    }

}

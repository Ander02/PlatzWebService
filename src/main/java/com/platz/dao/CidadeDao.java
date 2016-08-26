/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.dao;

import com.platz.model.CidadeModel;
import com.platz.model.EstadoModel;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author 15153770
 */
public class CidadeDao extends GenericDao<CidadeModel> {

    public List<CidadeModel> buscarPorEstado(EstadoModel estado) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<CidadeModel> lista = entityManager.createQuery("from CidadeModel where estado =:estado").setParameter("estado", estado).getResultList();
        entityManager.close();
        return lista;
    }

    public List<CidadeModel> buscarPorNome(String nome) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<CidadeModel> lista = entityManager.createQuery("from CidadeModel where nome like :nome").setParameter("nome", nome + "%").getResultList();
        entityManager.close();
        return lista;
    }

    public CidadeModel buscarPeloNomeEUf(String nome, String uf) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        CidadeModel model = (CidadeModel) entityManager.createQuery("from CidadeModel where nome = :nome and estado.uf = :uf").setParameter("nome", nome).setParameter("uf", uf).getSingleResult();
        entityManager.close();
        return model;
    }
}

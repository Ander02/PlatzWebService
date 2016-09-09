/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.dao;

import com.platz.model.ContaModel;
import com.platz.model.EventoModel;
import com.platz.model.PostagemModel;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author 15153770
 */
public class PostagemDao extends GenericDao<PostagemModel> {

    public List<PostagemModel> buscarPorEvento(EventoModel evento) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<PostagemModel> lista = entityManager.createQuery("from PostagemModel where evento =:evento").setParameter("evento", evento).getResultList();
        entityManager.close();
        return lista;
    }

    public List<PostagemModel> buscarPorConta(ContaModel conta) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<PostagemModel> lista = entityManager.createQuery("from PostagemModel where conta =:conta").setParameter("conta", conta).getResultList();
        entityManager.close();
        return lista;
    }
}

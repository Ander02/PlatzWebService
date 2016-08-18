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
}

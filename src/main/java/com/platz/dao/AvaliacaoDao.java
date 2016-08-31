/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.dao;

import com.platz.model.AvaliacaoModel;
import com.platz.model.EventoModel;
import com.platz.model.UsuarioModel;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author 15153770
 */
public class AvaliacaoDao extends GenericDao<AvaliacaoModel> {

    //buscar evento e usuario
    public List<AvaliacaoModel> buscarPorEvento(EventoModel evento) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<AvaliacaoModel> lista = entityManager.createQuery("from AvaliacaoModel where evento =:evento").setParameter("evento", evento).getResultList();
        entityManager.close();
        return lista;
    }

    public List<AvaliacaoModel> buscarPorUsuario(UsuarioModel usuario) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<AvaliacaoModel> lista = entityManager.createQuery("from AvaliacaoModel where usuario =:usuario").setParameter("usuario", usuario).getResultList();
        entityManager.close();
        return lista;
    }
}

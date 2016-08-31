/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.dao;

import com.platz.model.ContaModel;
import com.platz.model.EventoModel;
import com.platz.model.PresencaModel;
import com.platz.model.TipoPresenca;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author 15153770
 */
public class PresencaDao extends GenericDao<PresencaModel> {

    public List<PresencaModel> buscarPorEvento(EventoModel evento) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<PresencaModel> lista = entityManager.createQuery("from PresencaModel where evento =:evento").setParameter("evento", evento).getResultList();
        entityManager.close();
        return lista;
    }

    public List<PresencaModel> buscarPorConta(ContaModel conta) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<PresencaModel> lista = entityManager.createQuery("from PresencaModel where conta =:conta").setParameter("conta", conta).getResultList();
        entityManager.close();
        return lista;
    }

    public List<PresencaModel> buscarPorTipoPresenca(TipoPresenca tipoPresenca) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<PresencaModel> lista = entityManager.createQuery("from PresencaModel where tipoPresenca =:tipoPresenca").setParameter("tipoPresenca", tipoPresenca).getResultList();
        entityManager.close();
        return lista;
    }
}

package com.platz.dao;

import com.platz.model.CurtidaModel;
import com.platz.model.EventoModel;
import com.platz.model.UsuarioModel;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Anderson
 */
public class CurtidaDao extends GenericDao<CurtidaModel> {

    //buscar evento e usuario
    public List<CurtidaModel> buscarPorEvento(EventoModel evento) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<CurtidaModel> lista = entityManager.createQuery("from CurtidaModel where evento =:evento").setParameter("evento", evento).getResultList();
        entityManager.close();
        return lista;
    }

    public List<CurtidaModel> buscarPorUsuario(UsuarioModel usuario) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<CurtidaModel> lista = entityManager.createQuery("from CurtidaModel where usuario =:usuario").setParameter("usuario", usuario).getResultList();
        entityManager.close();
        return lista;
    }

    public CurtidaModel buscarPorEventoEUsuario(UsuarioModel usuario, EventoModel evento) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        CurtidaModel curtida = (CurtidaModel) entityManager.createQuery("from CurtidaModel where evento =:evento and usuario =:usuario").setParameter("evento", evento).setParameter("usuario", usuario).getSingleResult();
        entityManager.close();
        return curtida;      
    }

}

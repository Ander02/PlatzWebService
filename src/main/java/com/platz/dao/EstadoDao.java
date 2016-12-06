package com.platz.dao;

import com.platz.model.EstadoModel;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author 15153770
 */
public class EstadoDao extends GenericDao<EstadoModel> {

    @SuppressWarnings("unchecked")
    public EstadoModel buscarPelaUf(String uf) {
        uf = uf.toUpperCase();
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        EstadoModel model = (EstadoModel) entityManager.createQuery("from EstadoModel where uf = :uf").setParameter("uf", uf).getSingleResult();
        entityManager.close();
        return model;
    }

    @SuppressWarnings("unchecked")
    public List<EstadoModel> buscarPeloNome(String nome) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EstadoModel> lista = entityManager.createQuery("from EstadoModel where nome like :nome").setParameter("nome", nome + "%").getResultList();
        entityManager.close();
        return lista;
    }

}

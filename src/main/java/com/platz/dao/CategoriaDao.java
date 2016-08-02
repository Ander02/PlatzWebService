package com.platz.dao;

import com.platz.model.Categoria;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Anderson
 */
public class CategoriaDao extends GenericDao<Categoria> {

    @SuppressWarnings("unchecked")
    public List<Categoria> buscarPeloNome(String nome) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<Categoria> lista = entityManager.createQuery("from Categoria where nome like :nome")
                .setParameter("nome", nome + "%").getResultList();
        entityManager.close();
        return lista;
    }

}

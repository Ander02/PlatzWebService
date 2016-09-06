package com.platz.dao;

import com.platz.model.CategoriaModel;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Anderson
 */
public class CategoriaDao extends GenericDao<CategoriaModel> {

    public List<CategoriaModel> buscarPeloNome(String nome) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<CategoriaModel> lista = entityManager.createQuery("from CategoriaModel where nome like :nome")
                .setParameter("nome", nome + "%").getResultList();
        entityManager.close();
        return lista;
    }

}

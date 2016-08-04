package com.platz.dao;

import com.platz.model.AssuntoModel;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Anderson
 */
public class AssuntoDao extends GenericDao<AssuntoModel> {

    @SuppressWarnings("unchecked")
    public List<AssuntoModel> buscarPeloNome(String nome) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<AssuntoModel> lista = entityManager.createQuery("from AssuntoModel where nome like :nome")
                .setParameter("nome", nome + "%").getResultList();
        entityManager.close();
        return lista;
    }

}

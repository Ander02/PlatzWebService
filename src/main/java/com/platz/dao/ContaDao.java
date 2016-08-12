package com.platz.dao;

import com.platz.model.ContaModel;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Anderson
 */
public class ContaDao extends GenericDao<ContaModel> {

    public ContaModel getConta(String email, String senha) {

        try {
            EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
            ContaModel model = (ContaModel) entityManager.createQuery("from ContaModel where email=:email and senha=:senha")
                    .setParameter("email", email).setParameter("senha", senha).getSingleResult();
            entityManager.close();
            if (model == null) {
                System.out.println("Conta não Encontrada");
                return null;
            } else {
                return model;
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar conta");
            return null;
        }
    }

    public ContaModel buscarPeloEmail(String email) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        ContaModel model = (ContaModel) entityManager.createQuery("from ContaModel where email =:email")
                .setParameter("email", email).getSingleResult();
        entityManager.close();
        return model;
    }

    public List<ContaModel> buscarInativos() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<ContaModel> lista = entityManager.createQuery("from ContaModel where inativo !=:atividade").setParameter("atividade", null).getResultList();
        entityManager.close();

        return lista;
    }

    public List<ContaModel> buscarAtivos() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<ContaModel> lista = entityManager.createQuery("from ContaModel where inativo =:atividade").setParameter("atividade", null).getResultList();
        entityManager.close();

        return lista;
    }

}

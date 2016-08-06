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
            ContaModel conta = (ContaModel) entityManager.createQuery("from ContaModel where email=:email and senha=:senha")
                    .setParameter("email", email).setParameter("senha", senha).getSingleResult();
            entityManager.close();
            return conta;

        } catch (Exception e) {
            System.out.println("Erro ao buscar conta");
            return null;
        }

    }

    public List<ContaModel> buscarPeloEmail(String email) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<ContaModel> lista = entityManager.createQuery("from ContaModel where email like :email")
                .setParameter("email", email + "%").getResultList();
        entityManager.close();
        return lista;
    }

    public List<ContaModel> buscarPelaAtividade(Boolean atividade) {

        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<ContaModel> lista = entityManager.createQuery("from ContaModel where ativo=:atividade")
                .setParameter("status", atividade).getResultList();
        entityManager.close();
        return lista;

    }

}

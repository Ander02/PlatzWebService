package com.platz.dao;

import com.platz.model.AssuntoModel;
import com.platz.model.MensagemModel;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Anderson
 */
public class MensagemDao extends GenericDao<MensagemModel> {

    public List<MensagemModel> buscarPeloEmail(String email) {

        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();

        List<MensagemModel> lista = entityManager.createQuery("from MensagemModel where email=:email").setParameter("email", email).getResultList();
        entityManager.close();

        return lista;

    }

    public List<MensagemModel> buscarPeloAssunto(AssuntoModel assunto) {

        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();

        List<MensagemModel> lista = entityManager.createQuery("from MensagemModel where assunto=:assunto").setParameter("assunto", assunto).getResultList();
        entityManager.close();

        return lista;

    }

    public List<MensagemModel> buscarMarcadas() {

        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();

        List<MensagemModel> lista = entityManager.createQuery("from MensagemModel where marcado=:true").getResultList();
        entityManager.close();

        return lista;

    }

}

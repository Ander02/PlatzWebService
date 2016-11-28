package com.platz.dao;

import com.platz.model.ContaModel;
import com.platz.model.EventoModel;
import com.platz.model.PostagemModel;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author 15153770
 */
public class PostagemDao extends GenericDao<PostagemModel> {

    @Override
    public void excluir(PostagemModel model) {
        model.setDeletado(new Date());
        super.alterar(model);
    }

    public List<PostagemModel> buscarPorEvento(EventoModel evento) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<PostagemModel> lista = entityManager.createQuery("from PostagemModel where evento =:evento and censurado =:censurado and deletado =:deletado")
                .setParameter("deletado", null).setParameter("censurado", null).setParameter("evento", evento).getResultList();
        entityManager.close();
        return lista;
    }

    public List<PostagemModel> buscarPorConta(ContaModel conta) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<PostagemModel> lista = entityManager.createQuery("from PostagemModel where conta =:conta  and censurado =:censuradoand deletado =:deletado")
                .setParameter("deletado", null).setParameter("censurado", null).setParameter("conta", conta).getResultList();
        entityManager.close();
        return lista;
    }
}

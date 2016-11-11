package com.platz.dao;

import com.platz.model.AvaliacaoModel;
import com.platz.model.EventoModel;
import com.platz.model.UsuarioModel;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author 15153770
 */
public class AvaliacaoDao extends GenericDao<AvaliacaoModel> {

    @Override
    public void cadastrar(AvaliacaoModel avaliacao) {
        AvaliacaoModel av = this.buscarPorEventoEUsuario(avaliacao.getEvento(), avaliacao.getUsuario());
        if (av == null) {
            super.cadastrar(avaliacao);
        } else {
            avaliacao.setId(av.getId());
            super.alterar(avaliacao);
        }
    }

    //buscar evento e usuario
    public List<AvaliacaoModel> buscarPorEvento(EventoModel evento) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<AvaliacaoModel> lista = entityManager.createQuery("from AvaliacaoModel where evento =:evento").setParameter("evento", evento).getResultList();
        entityManager.close();
        return lista;
    }

    public AvaliacaoModel buscarPorEventoEUsuario(EventoModel evento, UsuarioModel usuario) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        try {
            AvaliacaoModel avaliacao = (AvaliacaoModel) entityManager.createQuery("from AvaliacaoModel where evento =:evento and usuario =:usuario").setParameter("evento", evento)
                    .setParameter("usuario", usuario).getSingleResult();
            System.out.println(avaliacao);
            entityManager.close();
            return avaliacao;
        } catch (Exception e) {
            System.out.println("Erro ao buscar Avaliação" + e.getMessage());
            return null;
        }
    }

    public List<AvaliacaoModel> buscarPorUsuario(UsuarioModel usuario) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<AvaliacaoModel> lista = entityManager.createQuery("from AvaliacaoModel where usuario =:usuario").setParameter("usuario", usuario).getResultList();
        entityManager.close();
        return lista;
    }
}

package com.platz.dao;

import com.platz.model.CidadeModel;
import com.platz.model.EstadoModel;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author 15153770
 */
public class CidadeDao extends GenericDao<CidadeModel> {

    public List<CidadeModel> buscarPorEstado(EstadoModel estado) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<CidadeModel> lista = entityManager.createQuery("from CidadeModel where estado =:estado").setParameter("estado", estado).getResultList();
        entityManager.close();
        return lista;
    }

    public List<CidadeModel> buscarPorNome(String nome) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<CidadeModel> lista = entityManager.createQuery("from CidadeModel where nome like :nome").setParameter("nome", "%" + nome + "%").getResultList();
        entityManager.close();
        return lista;
    }

    public CidadeModel buscarPeloNomeEUf(String nome, String uf) {
        try {
            EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
            EstadoModel estado = new EstadoDao().buscarPelaUf(uf.toUpperCase());
            CidadeModel model = (CidadeModel) entityManager.createQuery("select c from CidadeModel c where c.nome = :nome and c.estado = :estado").setParameter("nome", nome).setParameter("estado", estado).getSingleResult();
            entityManager.close();
            return model;
        } catch (Exception e) {
            System.out.println("Erro ao buscar Cidade pelo nome e pela UF :" + e.getMessage());
            return null;
        }

    }
}

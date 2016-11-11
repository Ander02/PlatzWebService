package com.platz.dao;

import com.platz.model.ContaModel;
import com.platz.model.EventoModel;
import com.platz.model.PresencaModel;
import com.platz.model.TipoPresenca;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author 15153770
 */
public class PresencaDao extends GenericDao<PresencaModel> {

    @Override
    public void cadastrar(PresencaModel presenca) {
        PresencaModel pre = this.buscarPorEventoEConta(presenca.getEvento(), presenca.getConta());
        if (pre == null) {
            super.cadastrar(presenca);
        } else {
            presenca.setId(pre.getId());
            super.alterar(presenca);
        }
    }

    public PresencaModel buscarPorEventoEConta(EventoModel evento, ContaModel conta) {

        try {

            EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
            PresencaModel model = (PresencaModel) entityManager.createQuery("from PresencaModel where conta =:conta and evento =:evento").setParameter("conta", conta)
                    .setParameter("evento", evento).getSingleResult();
            entityManager.close();
            return model;
        } catch (Exception e) {
            e.printStackTrace();            
            return null;
        }

    }

    public List<PresencaModel> buscarPorEvento(EventoModel evento) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<PresencaModel> lista = entityManager.createQuery("from PresencaModel where evento =:evento").setParameter("evento", evento).getResultList();
        entityManager.close();
        return lista;
    }

    public List<PresencaModel> buscarPorConta(ContaModel conta) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<PresencaModel> lista = entityManager.createQuery("from PresencaModel where conta =:conta").setParameter("conta", conta).getResultList();
        entityManager.close();
        return lista;
    }

    public List<PresencaModel> buscarPorTipoPresenca(TipoPresenca tipoPresenca) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<PresencaModel> lista = entityManager.createQuery("from PresencaModel where tipoPresenca =:tipoPresenca").setParameter("tipoPresenca", tipoPresenca).getResultList();
        entityManager.close();
        return lista;
    }
}

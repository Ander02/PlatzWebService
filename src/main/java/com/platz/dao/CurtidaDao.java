package com.platz.dao;

import com.platz.model.CurtidaModel;
import com.platz.model.EventoModel;
import com.platz.model.UsuarioModel;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Anderson
 */
public class CurtidaDao extends GenericDao<CurtidaModel> {

    @Override
    public void cadastrar(CurtidaModel curtida) {
        CurtidaModel ct = this.buscarPorEventoEUsuario(curtida.getUsuario(), curtida.getEvento());
        if (ct == null) {
            super.cadastrar(curtida);
        } else {
            curtida.setId(ct.getId());
            super.alterar(curtida);
        }
    }

    //buscar evento e usuario
    public List<CurtidaModel> buscarPorEvento(EventoModel evento) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<CurtidaModel> lista = entityManager.createQuery("from CurtidaModel where evento =:evento and curtida= :curtida").setParameter("curtida", true).setParameter("evento", evento).getResultList();
        entityManager.close();
        return lista;
    }

    public List<CurtidaModel> buscarPorUsuario(UsuarioModel usuario) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<CurtidaModel> lista = entityManager.createQuery("from CurtidaModel where usuario =:usuario and curtida= :curtida").setParameter("curtida", true).setParameter("usuario", usuario).getResultList();
        entityManager.close();
        return lista;
    }

    public CurtidaModel buscarPorEventoEUsuario(UsuarioModel usuario, EventoModel evento) {
        try {
            EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
            CurtidaModel curtida = (CurtidaModel) entityManager.createQuery("from CurtidaModel where evento =:evento and usuario =:usuario")
                    .setParameter("evento", evento).setParameter("usuario", usuario).getSingleResult();
            entityManager.close();
            return curtida;
        } catch (Exception e) {
            System.out.println("Erro ao buscar Curtida " + e.getMessage());
            return null;
        }

    }

}

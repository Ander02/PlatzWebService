package com.platz.dao;

import com.platz.model.ContaModel;
import com.platz.model.EmpresaModel;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author 15153770
 */
public class EmpresaDao extends GenericDao<EmpresaModel> {

    public EmpresaModel bucarPeloCNPJ(String cnpj) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        EmpresaModel model = (EmpresaModel) entityManager.createQuery("from EmpresaModel where cnpj = :cnpj").setParameter("cnpj", cnpj).getSingleResult();
        entityManager.close();
        return model;
    }

    public List<EmpresaModel> bucarPeloNome(String nomeFantasia) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EmpresaModel> model = entityManager.createQuery("from EmpresaModel where nomeFantasia like :nome").setParameter("nome", nomeFantasia + "%").getResultList();
        entityManager.close();
        return model;
    }

    public EmpresaModel buscarPelaConta(ContaModel conta) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        EmpresaModel model = (EmpresaModel) entityManager.createQuery("from EmpresaModel where conta = :conta").setParameter("conta", conta).getSingleResult();
        return model;
    }
}

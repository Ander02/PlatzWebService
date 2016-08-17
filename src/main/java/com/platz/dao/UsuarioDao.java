/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.dao;

import com.platz.model.ContaModel;
import com.platz.model.UsuarioModel;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author 15153770
 */
public class UsuarioDao extends GenericDao<UsuarioModel>{
    
    public List<UsuarioModel> bucarPeloNome(String nome){
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<UsuarioModel> lista = entityManager.createQuery("from UsuarioModel where nome like :nome").setParameter("nome", nome+"%").getResultList();
        entityManager.close();
        return lista;
    }

    public UsuarioModel bucarCPF(String cpf){
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        UsuarioModel model = (UsuarioModel) entityManager.createQuery("from UsuarioModel where cpf = :cpf").setParameter("cpf", cpf).getSingleResult();
        entityManager.close();
        return model;
    }
      public UsuarioModel buscarPelaConta(ContaModel conta){
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        UsuarioModel model = (UsuarioModel) entityManager.createQuery("form UsuarioModel where conta = :conta").setParameter("conta", conta).getSingleResult();
        return model;
    }
}

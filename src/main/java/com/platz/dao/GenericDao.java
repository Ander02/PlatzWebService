package com.platz.dao;

import java.util.List;
import javax.persistence.EntityManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
/**
 *
 * @author Anderson
 * @param <T>
 */
public abstract class GenericDao<T> {

    public void cadastrar(T t) {

        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void alterar(T t) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(t);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @SuppressWarnings("unchecked")
    public List<T> listarTodos(Class<T> entity) {

        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<T> lista = entityManager.createQuery("from " + entity.getSimpleName()).getResultList();
        entityManager.close();

        return lista;
    }

    public T buscarPorId(Class<T> entity, String id) {

        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        T resultado = entityManager.find(entity, id);
        entityManager.close();

        return resultado;

    }

    public void excluir(T t) {

        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        t = entityManager.merge(t);
        entityManager.remove(t);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}

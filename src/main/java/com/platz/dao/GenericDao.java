package com.platz.dao;

import java.util.List;
import javax.persistence.EntityManager;
import org.bson.types.ObjectId;

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
    public List<T> listarTodos(Class<T> model) {

        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<T> lista = entityManager.createQuery("from " + model.getSimpleName()).getResultList();
        entityManager.close();

        return lista;
    }

    public T buscarPorId(Class<T> model, String id) {

        try {

            EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
            T resultado = entityManager.find(model, new ObjectId(id));
            entityManager.close();

            return resultado;

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }

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

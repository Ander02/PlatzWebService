package com.platz.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Administrador
 */
public class JPAUtil {

    private EntityManagerFactory factory;
    private static JPAUtil instance;

    public JPAUtil() {
        this.factory = Persistence.createEntityManagerFactory("platz");
    }

    public static synchronized JPAUtil getInstance() {
        if (instance == null) {
            instance = new JPAUtil();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

}

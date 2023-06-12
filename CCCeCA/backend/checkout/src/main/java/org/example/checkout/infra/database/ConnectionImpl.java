package org.example.checkout.infra.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionImpl implements Connection {
    private static final String PERSISTENCE_UNIT_NAME = "cccat";
    private EntityManager entityManager;

    public ConnectionImpl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = emf.createEntityManager();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void close() {
        entityManager.close();
    }
}

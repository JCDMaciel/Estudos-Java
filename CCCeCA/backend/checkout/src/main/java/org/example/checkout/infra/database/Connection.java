package org.example.checkout.infra.database;

import javax.persistence.EntityManager;

public interface Connection {
    EntityManager getEntityManager();
    void close();
}

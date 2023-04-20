package br.com.desafio_05.infra;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("desafio_05");
    public EntityManager em = factory.createEntityManager();
}

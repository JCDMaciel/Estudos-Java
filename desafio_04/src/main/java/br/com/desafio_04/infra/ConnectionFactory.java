package br.com.desafio_04.infra;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("desafio_04");
    public EntityManager em = factory.createEntityManager();
}

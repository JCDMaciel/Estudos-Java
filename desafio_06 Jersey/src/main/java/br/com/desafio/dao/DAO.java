package br.com.desafio.dao;

import br.com.desafio.infra.ConnectionFactory;

import javax.persistence.EntityManager;

public abstract class DAO<T> {
    ConnectionFactory factory = new ConnectionFactory();
    public EntityManager em = factory.em;

    public T save(T t) {
        ConnectionFactory factory = new ConnectionFactory();
        EntityManager em = factory.em;

        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
        return t;
    }
    public T update(Object t, Long id) {
        ConnectionFactory factory = new ConnectionFactory();
        EntityManager em = factory.em;

        try {
            em.getTransaction().begin();
            T objAtualizado = (T) em.find(t.getClass(), id);
            em.merge(t);
            em.getTransaction().commit();
            return objAtualizado;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    public void delete(T t, Long id) {
        ConnectionFactory factory = new ConnectionFactory();
        EntityManager em = factory.em;

        try {
            em.getTransaction().begin();
            T objDeletado = (T) em.find(t.getClass(), id);
            em.remove(objDeletado);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}

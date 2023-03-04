package com.example.restservice.models;

import jakarta.websocket.Session;

public class PessoaDAO {
    private SessionFactory sessionFactory;

    public PessoaDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Pessoa pessoa) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(pessoa);
        session.getTransaction().commit();
    }

    public Pessoa getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Pessoa pessoa = session.get(Pessoa.class, id);
        session.getTransaction().commit();
        return pessoa;
    }

    public void update(Pessoa pessoa) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(pessoa);
        session.getTransaction().commit();
    }

    public void delete(Pessoa pessoa) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(pessoa);
        session.getTransaction().commit();
    }
}


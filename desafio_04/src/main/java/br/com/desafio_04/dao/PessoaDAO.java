package br.com.desafio_04.dao;

import br.com.desafio_04.infra.ConnectionFactory;
import br.com.desafio_04.model.Pessoa;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import javax.persistence.EntityManager;
import java.util.List;

public class PessoaDAO extends DAO {

    ConnectionFactory factory = new ConnectionFactory();
    public EntityManager em = factory.em;

    public List<Pessoa> findAllPessoas() {
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Pessoa.class, "bean");
        ProjectionList projectionList = Projections.projectionList();

        projectionList.add(Projections.property("bean.id").as("id"));
        projectionList.add(Projections.property("bean.nome").as("nome"));
        projectionList.add(Projections.property("bean.cpf").as("cpf"));
        projectionList.add(Projections.property("bean.email").as("email"));
        projectionList.add(Projections.property("bean.dataDeNascimento").as("dataDeNascimento"));
        projectionList.add(Projections.property("bean.endereco").as("endereco"));

        criteria.setProjection(projectionList);
        criteria.setResultTransformer(new AliasToBeanResultTransformer(Pessoa.class));

        return criteria.list();
    }

    public <T> T findPessoaById(Long id) {
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Pessoa.class, "bean");
        ProjectionList projectionList = Projections.projectionList();

        projectionList.add(Projections.property("bean.id").as("id"));
        projectionList.add(Projections.property("bean.nome").as("nome"));
        projectionList.add(Projections.property("bean.cpf").as("cpf"));
        projectionList.add(Projections.property("bean.email").as("email"));
        projectionList.add(Projections.property("bean.dataDeNascimento").as("dataDeNascimento"));
        projectionList.add(Projections.property("bean.endereco").as("endereco"));

        Criterion restricao = Restrictions.eq("bean.id", id);

        criteria.add(restricao);

        criteria.setProjection(projectionList);
        criteria.setResultTransformer(new AliasToBeanResultTransformer(Pessoa.class));

        Pessoa pessoa = (Pessoa) criteria.uniqueResult();

        return (T) pessoa;
    }
}

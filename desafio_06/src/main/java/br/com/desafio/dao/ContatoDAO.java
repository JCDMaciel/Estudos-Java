package br.com.desafio.dao;

import br.com.desafio.infra.ConnectionFactory;
import br.com.desafio.model.Contato;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import javax.persistence.EntityManager;
import java.util.List;

public class ContatoDAO extends DAO{
    ConnectionFactory factory = new ConnectionFactory();
    public EntityManager em = factory.em;

    public List<Contato> findAllContatos(Long id) {
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Contato.class, "bean");
        ProjectionList projectionList = Projections.projectionList();

        projectionList.add(Projections.property("bean.idDoContato").as("idDoContato"));
        projectionList.add(Projections.property("bean.nomeDoContato").as("nomeDoContato"));
        projectionList.add(Projections.property("bean.telefoneDoContato").as("telefoneDoContato"));
        projectionList.add(Projections.property("bean.emailDoContato").as("emailDoContato"));

        criteria.createAlias("bean.pessoa", "pessoa");

        Criterion restricao = Restrictions.eq("pessoa.id", id);

        criteria.add(restricao);

        criteria.setProjection(projectionList);
        criteria.setResultTransformer(new AliasToBeanResultTransformer(Contato.class));

        return criteria.list();
    }
}

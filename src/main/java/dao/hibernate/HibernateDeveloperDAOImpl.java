package dao.hibernate;

import dao.DeveloperDAO;
import entities.Developer;
import hibernateFunctionality.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateDeveloperDAOImpl implements DeveloperDAO {

    @Override
    public void save(Developer developer) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(developer);
        transaction.commit();
        session.close();
    }

    @Override
    public Developer getById(Integer id) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Developer developer = session.get(Developer.class, id);
        session.close();
        return developer;
    }

    @Override
    public void remove(Integer id) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(getById(id));
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Developer developer) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(developer);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Developer> getAll() {
        Session session = HibernateFactory.getSessionFactory().openSession();

        List<Developer> developers = session.createQuery("from Developer").list();
        session.close();
        return developers;
    }
}

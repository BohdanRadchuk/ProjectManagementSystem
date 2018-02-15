package dao.hibernate;

import dao.DeveloperDAO;
import entities.Developer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class HibernateDeveloperDAOImpl implements DeveloperDAO{

    private SessionFactory sessionFactory;

    public HibernateDeveloperDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Developer developer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(developer);
        transaction.commit();
        session.close();
    }

    @Override
    public Developer getById(Integer id) {
        Session session = sessionFactory.openSession();
        Developer developer = session.get(Developer.class, id);
        session.close();
        return developer;
    }

    @Override
    public void remove(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(getById(id));
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Developer developer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(developer);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Developer> getAll() {
        Session session = sessionFactory.openSession();

        List<Developer> developers = session.createQuery("from Developer").list();
        session.close();
        return developers;
    }
}

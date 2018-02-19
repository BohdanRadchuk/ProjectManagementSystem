package dao.hibernate;

import dao.SkillsDAO;
import entities.Skills;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateSkillsDAOImpl implements SkillsDAO {

    private SessionFactory sessionFactory;

    public HibernateSkillsDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void save(Skills skills) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(skills);
        transaction.commit();
        session.close();
    }

    @Override
    public Skills getById(Integer id) {
        Session session = sessionFactory.openSession();
        Skills skills = session.get(Skills.class, id);
        session.close();
        return skills;
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
    public void update(Skills skill) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(skill);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Skills> getAll() {
        Session session = sessionFactory.openSession();
        List<Skills> skills = session.createQuery("from Skills").list();
        session.close();
        return skills;
    }
}

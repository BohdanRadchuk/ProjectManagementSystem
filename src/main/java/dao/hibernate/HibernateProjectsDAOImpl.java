package dao.hibernate;

import dao.ProjectsDAO;

import entities.Projects;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateProjectsDAOImpl implements ProjectsDAO {

    private SessionFactory sessionFactory;

    public HibernateProjectsDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Projects projects) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(projects);
        transaction.commit();
        session.close();
    }

    @Override
    public Projects getById(Integer id) {
        Session session = sessionFactory.openSession();
        Projects project = session.get(Projects.class, id);
        session.close();
        return project;
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
    public void update(Projects project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(project);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Projects> getAll() {
        Session session = sessionFactory.openSession();
        List<Projects> developers = session.createQuery("from Projects").list();
        session.close();
        return developers;
    }
}

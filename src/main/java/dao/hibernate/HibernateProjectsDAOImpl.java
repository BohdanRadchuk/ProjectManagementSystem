package dao.hibernate;

import dao.ProjectsDAO;
import entities.Projects;
import hibernateFunctionality.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateProjectsDAOImpl implements ProjectsDAO {

    @Override
    public void save(Projects projects) {

        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(projects);
        transaction.commit();
        session.close();
    }

    @Override
    public Projects getById(Integer id) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Projects project = session.get(Projects.class, id);
        session.close();
        return project;
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
    public void update(Projects project) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(project);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Projects> getAll() {
        Session session = HibernateFactory.getSessionFactory().openSession();
        List<Projects> projects = session.createQuery("from Projects").list();
        session.close();
        return projects;
    }
}

package dao.hibernate;

import dao.CompaniesDAO;
import entities.Companies;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateCompaniesDAOImpl implements CompaniesDAO {

    private SessionFactory sessionFactory;

    public HibernateCompaniesDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Companies companies) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(companies);
        transaction.commit();
        session.close();
    }

    @Override
    public Companies getById(Integer id) {
        Session session = sessionFactory.openSession();
        Companies company = session.get(Companies.class, id);
        session.close();
        return company;
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
    public void update(Companies company) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(company);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Companies> getAll() {
        Session session = sessionFactory.openSession();

        List<Companies> companies = session.createQuery("from Companies").list();
        session.close();
        return companies;
    }
}

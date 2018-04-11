package dao.hibernate;

import dao.CompaniesDAO;
import entities.Companies;
import hibernateFunctionality.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateCompaniesDAOImpl implements CompaniesDAO {

    @Override
    public void save(Companies companies) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(companies);
        transaction.commit();
        session.close();
    }

    @Override
    public Companies getById(Integer id) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Companies company = session.get(Companies.class, id);
        session.close();
        return company;
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
    public void update(Companies company) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(company);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Companies> getAll() {
        Session session = HibernateFactory.getSessionFactory().openSession();

        List<Companies> companies = session.createQuery("from Companies").list();
        session.close();
        return companies;
    }
}

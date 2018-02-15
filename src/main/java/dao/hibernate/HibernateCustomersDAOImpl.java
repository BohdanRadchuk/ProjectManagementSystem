package dao.hibernate;

import dao.CustomersDAO;
import entities.Companies;
import entities.Customers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateCustomersDAOImpl implements CustomersDAO{

    private SessionFactory sessionFactory;

    public HibernateCustomersDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Customers customers) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customers);
        transaction.commit();
        session.close();
    }

    @Override
    public Customers getById(Integer id) {
        Session session = sessionFactory.openSession();
        Customers customer = session.get(Customers.class, id);
        session.close();
        return customer;
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
    public void update(Customers customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Customers> getAll() {
        Session session = sessionFactory.openSession();

        List<Customers> customers = session.createQuery("from Customers").list();
        session.close();
        return customers;
    }
}
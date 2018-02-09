package dao.hibernate;

import dao.DeveloperDAO;
import entities.Developer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateDeveloperDAOImpl implements DeveloperDAO{

    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public void save(Developer developer) {

    }

    @Override
    public Developer getById(Integer integer) {
        return null;
    }

    @Override
    public void remove(Integer integer) {

    }

    @Override
    public List<Developer> getAll() {
        return null;
    }
}

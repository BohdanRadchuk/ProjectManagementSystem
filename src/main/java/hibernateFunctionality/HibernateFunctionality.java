package hibernateFunctionality;

import dao.hibernate.HibernateCustomersDAOImpl;
import dao.hibernate.HibernateDeveloperDAOImpl;
import dao.hibernate.HibernateProjectsDAOImpl;
import entities.Customers;
import entities.Developer;
import entities.Projects;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;

public class HibernateFunctionality {

    private SessionFactory sessionFactory;
    private HibernateProjectsDAOImpl hbProjImpl = new HibernateProjectsDAOImpl(sessionFactory);
    private HibernateDeveloperDAOImpl hbDevImpl = new HibernateDeveloperDAOImpl(sessionFactory);
    private HibernateCustomersDAOImpl hbCustImpl = new HibernateCustomersDAOImpl(sessionFactory);

    public HibernateFunctionality() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void createNewTable (String name, String params){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createNativeQuery("CREATE TABLE IF NOT EXISTS " + name + " (" + params + ");");
        transaction.commit();
        session.close();
    }

    public void createNewProject (String projectName, String description, int cost){
        Projects project = new Projects();
        project.setProjectName(projectName);
        project.setDescription(description);
        project.setCost(cost);
        hbProjImpl.save(project);
    }

    public void createNewDeveloper (String firstName, String secondaryName, int age, String gender, long salary){
        Developer developer = new Developer();
        developer.setFirstName(firstName);
        developer.setSecondaryName(secondaryName);
        developer.setAge(age);
        developer.setGender(gender);
        developer.setSalary(BigDecimal.valueOf(salary));
        hbDevImpl.save(developer);
    }

    public void createNewCustomer (String custName, byte custStOrPr){
        Customers customer = new Customers();
        customer.setCustomerName(custName);
        customer.setStateOrPrivate(custStOrPr);
        hbCustImpl.save(customer);
    }

}

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
import java.util.List;

public class HibernateFunctionality {

    private SessionFactory sessionFactory;
    private HibernateProjectsDAOImpl hbProjImpl = new HibernateProjectsDAOImpl(sessionFactory);
    private HibernateDeveloperDAOImpl hbDevImpl = new HibernateDeveloperDAOImpl(sessionFactory);
    private HibernateCustomersDAOImpl hbCustImpl = new HibernateCustomersDAOImpl(sessionFactory);
    private Developer developer;
    private Projects project;
    private Customers customer;

    public HibernateFunctionality() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    //создание новой таблицы
    public void hibCreateNewTable(String name, String params) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createNativeQuery("CREATE TABLE IF NOT EXISTS " + name + " (" + params + ");");
        transaction.commit();
        session.close();
    }

    //создание записей
    public void hibCreateNewProject(String projectName, String description, int cost) {
        project = new Projects();
        project.setProjectName(projectName);
        project.setDescription(description);
        project.setCost(cost);
        hbProjImpl.save(project);
    }

    public void hibCreateNewDeveloper(String firstName, String secondaryName, int age, String gender, long salary) {
        developer = new Developer();
        developer.setFirstName(firstName);
        developer.setSecondaryName(secondaryName);
        developer.setAge(age);
        developer.setGender(gender);
        developer.setSalary(BigDecimal.valueOf(salary));
        hbDevImpl.save(developer);
    }

    public void hibCreateNewCustomer(String custName, byte custStOrPr) {
        customer = new Customers();
        customer.setCustomerName(custName);
        customer.setStateOrPrivate(custStOrPr);
        hbCustImpl.save(customer);
    }

    public void hibGetSumOfProjectSalary(int id) {
        Session session = sessionFactory.openSession();


        session.close();
    }

    /*System.out.println("Выберите данные которые хотите считать");
        System.out.println("1 - Вывод зарплаты всех разработчиков отдельного проекта:");
        System.out.println("2 - Вывод списка разработчиков отдельного проекта:");
        System.out.println("3 - Вывод списка всех Java разработчиков");
        System.out.println("4 - Вывод списка всех middle  разработчиков");
        System.out.println("5 - Вывод списка проэктов и количества разработчиков на них");
*/
    public void hbGetDevelopersOfProject(int id_project){
        Session session = sessionFactory.openSession();
        List<Developer> developers = session.createQuery("from Developer d where d.projects_id = " + id_project + ";").list();
        session.close();
        for (Developer d : developers) {
            System.out.println(d);
        }
    }
    public void hbGetJavaDevelopers(){
        Session session = sessionFactory.openSession();
        List<Developer> developers = session.createQuery("from Developer d where d.skills.Branch equals 'Java';").list();
        session.close();
        for (Developer d : developers) {
            System.out.println(d);
        }
    }


    public void hbGetMiddleDevelopers(){
        Session session = sessionFactory.openSession();
        List<Developer> developers = session.createQuery("from Developer d where d.skills.Level equals 'Middle';").list();
        session.close();
        for (Developer d : developers) {
            System.out.println(d);
        }
    }

    //обновление записей
    public void hibUpdateProject(int prId, String prName, String prDescr, int prCost) {
        project = new Projects();
        project.setId_project(prId);
        project.setProjectName(prName);
        project.setDescription(prDescr);
        project.setCost(prCost);
        hbProjImpl.update(project);
    }

    public void hibUpdateDeveloper (int devId, String devName, String devSecName, int devAge, String devGend, int devSalary){
        developer = new Developer();
        developer.setId(devId);
        developer.setFirstName(devName);
        developer.setSecondaryName(devSecName);
        developer.setAge(devAge);
        developer.setGender(devGend);
        developer.setSalary(BigDecimal.valueOf(devSalary));
        hbDevImpl.update(developer);
    }

    public void hibUpdateCustomer (int custId, String custName, byte stOrPr){
        customer = new Customers();
        customer.setId_customer(custId);
        customer.setCustomerName(custName);
        customer.setStateOrPrivate(stOrPr);
        hbCustImpl.update(customer);
    }

    //удаление записей
    public void hibDeleteProject (int id){
        hbProjImpl.remove(id);
    }

    public void hibDeleteDeveloper (int id){
        hbDevImpl.remove(id);
    }
    public void hibDeleteCustomer (int id){
        hbCustImpl.remove(id);
    }


}

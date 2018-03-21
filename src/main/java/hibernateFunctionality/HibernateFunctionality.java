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
import org.hibernate.query.NativeQuery;
import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.ArrayList;
import java.util.List;

public class HibernateFunctionality {

   // private static final SessionFactory SESSION_FACTORY = buildSessionFactory();
    private HibernateProjectsDAOImpl hbProjImpl = new HibernateProjectsDAOImpl();
    private HibernateDeveloperDAOImpl hbDevImpl = new HibernateDeveloperDAOImpl();
    private HibernateCustomersDAOImpl hbCustImpl = new HibernateCustomersDAOImpl();
    private Developer developer;
    private Projects project;
    private Customers customer;

/*    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }*/

    //создание новой таблицы - !не работает
    public void hibCreateNewTable(String name, String params) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createNativeQuery("CREATE TABLE IF NOT EXISTS " + name + " (" + params + ");");
        transaction.commit();
        session.close();
    }

    //создание записей
    //проэкта
    public void hibCreateNewProject(String projectName, String description, int cost) {
        project = new Projects();
        project.setProjectName(projectName);
        project.setDescription(description);
        project.setCost(cost);
        hbProjImpl.save(project);
    }

    //разработчика
    public void hibCreateNewDeveloper(String firstName, String secondaryName, int age, String gender, long salary) {
        developer = new Developer();
        developer.setFirstName(firstName);
        developer.setSecondaryName(secondaryName);
        developer.setAge(age);
        developer.setGender(gender);
        developer.setSalary(BigDecimal.valueOf(salary));
        hbDevImpl.save(developer);
    }

    //пользователя
    public void hibCreateNewCustomer(String custName, byte custStOrPr) {
        customer = new Customers();
        customer.setCustomerName(custName);
        customer.setStateOrPrivate(custStOrPr);
        hbCustImpl.save(customer);
    }

    //считывание даных
    //Вывод зарплаты всех разработчиков отдельного проекта
    public void hbGetSumOfProjectSalary(int projId) {
        String sql = "SELECT sum(developers.salary) AS SumOfSalary FROM developers, developer_projects " +
                "WHERE developers.id_dev IN ( SELECT DISTINCT developer_projects.id_dev where developer_projects.id_project = " + projId + ");";

        Session session = HibernateFactory.getSessionFactory().openSession();

        NativeQuery query = session.createNativeQuery(sql);
        BigDecimal result = (BigDecimal) query.uniqueResult();
        System.out.println("Сума зарплаты всех разработчиков " + projId + " проэкта = " + result);
        session.close();
    }

    //Вывод списка разработчиков отдельного проекта
    public void hbGetDevelopersOfProject(int id_project) {
        String sql = "SELECT DISTINCT developers.firstName, developers.secondaryName " +
                "FROM developers, developer_projects " +
                "WHERE  developers.id_dev IN (" +
                "SELECT developer_projects.id_dev " +
                "WHERE (developer_projects.id_project = " + id_project + "));";
        hbGetDeveloperListQuery(sql);
    }

    //Вывод списка всех Java разработчиков
    public void hbGetJavaDevelopers() {
        String sql = "select DISTINCT developers.firstName, developers.secondaryName " +
                "from developers, developer_skill " +
                "where  developers.id_dev IN (" +
                "select  developer_skill.id_dev " +
                "where (developer_skill.id_skill in (select id_skill from skills where branch like 'Java' )));";
        hbGetDeveloperListQuery(sql);
    }

    //Вывод списка всех middle  разработчиков
    public void hbGetMiddleDevelopers() {
        String sql = "select DISTINCT developers.firstName, developers.secondaryName " +
                "from developers, developer_skill " +
                "where  developers.id_dev IN (" +
                "select  developer_skill.id_dev " +
                "where (developer_skill.id_skill in (select id_skill from skills where skill_level like 'Middle')));";
        hbGetDeveloperListQuery(sql);
    }

    //метод для вывода списка имен и фамилий разработчиков в зависимости от выборки
    private void hbGetDeveloperListQuery(String sqlRequest) {
        Session session = HibernateFactory.getSessionFactory().openSession();

        NativeQuery query = session.createNativeQuery(sqlRequest);
        List<Object[]> rows = (List<Object[]>) query.list();

        for (Object[] row : rows) {
            String fsname = (String) row[0];
            String lastName = (String) row[1];

            System.out.println(fsname + ", " + lastName);
        }
        session.close();
    }

    //Вывод списка проэктов и количества разработчиков на них
    public void hbGetProjectsInfo() {
        List<String> result = new ArrayList<>();
        String sql = "select cost, ProjectName, count(developer_projects.id_dev) as DevelopersCount " +
                "from projects, developer_projects " +
                "where projects.id_project = developer_projects.id_project " +
                "group by projects.id_project;";
        Session session = HibernateFactory.getSessionFactory().openSession();

        NativeQuery query = session.createNativeQuery(sql);
        List<Object[]> rows = (List<Object[]>) query.list();

        for (Object[] row : rows) {
            Integer cost = (Integer) row[0];
            String projName = (String) row[1];
            BigInteger amountOfDevelopers = (BigInteger) row[2];

            System.out.println(cost + ", " + projName + ", " + amountOfDevelopers);
        }
        session.close();
    }

    //обновление записей
    public void hibUpdateProject(int prId, String prName, String prDescr, int prCost) {
        project = hbProjImpl.getById(prId);
        project.setId_project(prId);
        project.setProjectName(prName);
        project.setDescription(prDescr);
        project.setCost(prCost);
        hbProjImpl.update(project);
    }

    public void hibUpdateDeveloper(int devId, String devName, String devSecName, int devAge, String devGend, int devSalary) {
        developer = hbDevImpl.getById(devId);
        developer.setId(devId);
        developer.setFirstName(devName);
        developer.setSecondaryName(devSecName);
        developer.setAge(devAge);
        developer.setGender(devGend);
        developer.setSalary(BigDecimal.valueOf(devSalary));
        hbDevImpl.update(developer);
    }

    public void hibUpdateCustomer(int custId, String custName, byte stOrPr) {
        customer = hbCustImpl.getById(custId);
        customer.setId_customer(custId);
        customer.setCustomerName(custName);
        customer.setStateOrPrivate(stOrPr);
        hbCustImpl.update(customer);
    }

    //удаление записей
    public void hibDeleteProject(int id) {
        hbProjImpl.remove(id);
    }

    public void hibDeleteDeveloper(int id) {
        hbDevImpl.remove(id);
    }

    public void hibDeleteCustomer(int id) {
        hbCustImpl.remove(id);
    }

}

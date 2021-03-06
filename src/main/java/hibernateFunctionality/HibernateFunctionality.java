package hibernateFunctionality;

import dao.hibernate.HibernateCustomersDAOImpl;
import dao.hibernate.HibernateDeveloperDAOImpl;
import dao.hibernate.HibernateProjectsDAOImpl;
import entities.Customers;
import entities.Developer;
import entities.ProjectInfo;
import entities.Projects;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class HibernateFunctionality {

    private HibernateProjectsDAOImpl hbProjImpl = new HibernateProjectsDAOImpl();
    private HibernateDeveloperDAOImpl hbDevImpl = new HibernateDeveloperDAOImpl();
    private HibernateCustomersDAOImpl hbCustImpl = new HibernateCustomersDAOImpl();
    private Developer developer;
    private Projects project;
    private Customers customer;


    //create records
    //create project
    public void hibCreateNewProject(String projectName, String description, int cost) {
        project = new Projects();
        project.setProjectName(projectName);
        project.setDescription(description);
        project.setCost(cost);
        hbProjImpl.save(project);
    }

    //create developer
    public void hibCreateNewDeveloper(String firstName, String secondaryName, int age, String gender, long salary) {
        developer = new Developer();
        developer.setFirstName(firstName);
        developer.setSecondaryName(secondaryName);
        developer.setAge(age);
        developer.setGender(gender);
        developer.setSalary(BigDecimal.valueOf(salary));
        hbDevImpl.save(developer);
    }

    //create customer
    public void hibCreateNewCustomer(String custName, byte custStOrPr) {
        customer = new Customers();
        customer.setCustomerName(custName);
        customer.setStateOrPrivate(custStOrPr);
        System.out.println(custName);
        System.out.println(custStOrPr);
        hbCustImpl.save(customer);
    }

    //reading data
    //get salary of all developers working on a single project
    public void hbGetSumOfProjectSalary(int projId) {
        String sql = "SELECT sum(developers.salary) AS SumOfSalary FROM developers, developer_projects " +
                "WHERE developers.id_dev IN ( SELECT DISTINCT developer_projects.id_dev " +
                "where developer_projects.id_project = " + projId + ");";

        Session session = HibernateFactory.getSessionFactory().openSession();

        NativeQuery query = session.createNativeQuery(sql);
        BigDecimal result = (BigDecimal) query.uniqueResult();
        System.out.println("Сума зарплаты всех разработчиков " + projId + " проэкта = " + result);
        session.close();
    }

    //get all developers working on a single project
    public List<Developer> hbGetDevelopersOfProject(int id_project) {
        String sql = "SELECT DISTINCT developers.id_dev, developers.firstName, developers.secondaryName " +
                "FROM developers, developer_projects " +
                "WHERE  developers.id_dev IN (" +
                "SELECT developer_projects.id_dev " +
                "WHERE (developer_projects.id_project = " + id_project + "));";

        List<Developer> developers = hbGetDeveloperListQuery(sql);
        System.out.println(developers);
        return developers;
    }

    //get all Java developers
    public List<Developer> hbGetJavaDevelopers() {
        String sql = "select DISTINCT developers.id_dev, developers.firstName, developers.secondaryName " +
                "from developers, developer_skill " +
                "where  developers.id_dev IN (" +
                "select  developer_skill.id_dev " +
                "where (developer_skill.id_skill in (select id_skill from skills where branch like 'Java' )));";
        return hbGetDeveloperListQuery(sql);
    }

    //get all middle developers
    public List<Developer> hbGetMiddleDevelopers() {
        String sql = "select DISTINCT developers.id_dev, developers.firstName, developers.secondaryName " +
                "from developers, developer_skill " +
                "where  developers.id_dev IN (" +
                "select  developer_skill.id_dev " +
                "where (developer_skill.id_skill in (select id_skill from skills where skill_level like 'Middle')));";
        //hbGetDeveloperListQuery(sql);
        return hbGetDeveloperListQuery(sql);
    }

    //method that returns developers using different sql queries
    private List<Developer> hbGetDeveloperListQuery(String sqlRequest) {
        Session session = HibernateFactory.getSessionFactory().openSession();

        NativeQuery query = session.createNativeQuery(sqlRequest);
        List<Object[]> rows = (List<Object[]>) query.list();
        List<Developer> developers = new ArrayList<>();
        for (Object[] row : rows) {
            int id = (int) row[0];

            developers.add(hbDevImpl.getById(id));
            System.out.println();
        }
        session.close();
        return developers;
    }

    //get projects list with amount of developers on it
    public List<ProjectInfo> hbGetProjectsInfo() {

        String sql = "SELECT cost, ProjectName, count(developer_projects.id_dev) AS DevelopersCount " +
                "FROM projects, developer_projects " +
                "WHERE projects.id_project = developer_projects.id_project " +
                "GROUP BY projects.id_project;";
        Session session = HibernateFactory.getSessionFactory().openSession();

        NativeQuery query = session.createNativeQuery(sql);
        List<Object[]> rows = (List<Object[]>) query.list();
        List<ProjectInfo> projectInfos = new ArrayList<>();

        for (Object[] row : rows) {
            Integer cost = (Integer) row[0];
            String projName = (String) row[1];
            BigInteger amountOfDevelopers = (BigInteger) row[2];

            ProjectInfo oneProject = new ProjectInfo();
            oneProject.setProjectName(projName);
            oneProject.setCost(cost);
            oneProject.setDevelopersCount(amountOfDevelopers.intValue());
            projectInfos.add(oneProject);
            System.out.println(projName + ", " + cost + ", " + amountOfDevelopers);
        }
        session.close();
        return projectInfos;
    }

    //update records
    public void hibUpdateProject(int prId, String prName, String prDescr, int prCost) {
        project = hbProjImpl.getById(prId);
        project.setId_project(prId);
        project.setProjectName(prName);
        project.setDescription(prDescr);
        project.setCost(prCost);
        hbProjImpl.update(project);
    }

    public void hibUpdateDeveloper(int devId, String devName, String devSecName, int devAge,
                                   String devGend, long devSalary) {
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

    //delete records
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

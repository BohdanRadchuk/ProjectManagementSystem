package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Functionality {
    private final static String SETTINGS_PATH = "settings.ini";

    private Properties properties;

    private String dbUrl;
    private String dbUser;
    private String dbPass;
    private String dbDriver;

    private Connection connection;
    private Statement statement;

    //preparedStatements for creating operations
    private PreparedStatement addProjectSt;
    private PreparedStatement addDeveloperSt;
    private PreparedStatement addCustomerSt;
    //preparedStatements for update operations
    private PreparedStatement updateProjectSt;
    private PreparedStatement updateDeveloperSt;
    private PreparedStatement updateCustomerSt;
    //preparedStatements for delete operations
    private PreparedStatement deleteDeveloperSt;
    private PreparedStatement deleteProjectSt;
    private PreparedStatement deleteDeveloperProjectSt;
    private PreparedStatement deleteDeveloperSkillSt;
    private PreparedStatement deleteCompaniesProjectsSt;
    private PreparedStatement deleteCustomersProjectsSt;
    private PreparedStatement deleteCustomerSt;

    public Functionality() {
        readSettings();
        initConnection();
        initStatements();
    }

    private void readSettings() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(SETTINGS_PATH));
            dbDriver = properties.getProperty("db.driver");
            dbUrl = properties.getProperty("db.path");
            dbUser = properties.getProperty("db.user");
            dbPass = properties.getProperty("db.pass");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //creating connection to db
    private void initConnection() {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //initializing statements
    private void initStatements() {
        try {
            statement = connection.createStatement();
            addProjectSt = connection.prepareStatement("INSERT INTO projects (ProjectName, description, cost) " +
                    "VALUES (?, ?, ?);");
            addDeveloperSt = connection.prepareStatement("INSERT INTO developers (firstName, secondaryName, " +
                    "age, gender, salary) VALUES (?, ?, ?, ?, ?);");
            addCustomerSt = connection.prepareStatement("INSERT INTO customers (CustomerName, StateOrPrivate) " +
                    "VALUES (?, ?);");
            deleteDeveloperSt = connection.prepareStatement("DELETE FROM developers WHERE id_dev = ?;");
            deleteDeveloperProjectSt = connection.prepareStatement("DELETE FROM developer_projects " +
                    "WHERE id_project =? OR id_dev = ?;");
            deleteDeveloperSkillSt = connection.prepareStatement("DELETE FROM developer_skill " +
                    "WHERE id_skill = ? OR id_dev = ?;");
            deleteProjectSt = connection.prepareStatement("DELETE FROM projects WHERE id_project = ?;");
            deleteCompaniesProjectsSt = connection.prepareStatement("DELETE FROM companies_projects " +
                    "WHERE id_company = ? OR id_project = ?;");
            deleteCustomersProjectsSt = connection.prepareStatement("DELETE FROM customers_projects " +
                    "WHERE id_customer = ? OR id_project = ?;");
            deleteCustomerSt = connection.prepareStatement("DELETE FROM customers WHERE id_customer = ?;");
            updateProjectSt = connection.prepareStatement("UPDATE projects SET ProjectName = ? , " +
                    "description = ? , cost = ? WHERE id_project = ?;");
            updateDeveloperSt = connection.prepareStatement("UPDATE developers SET firstName = ? , " +
                    "secondaryName = ? , age = ?, gender = ?, salary = ? WHERE id_dev = ?;");
            updateCustomerSt = connection.prepareStatement("UPDATE customers SET CustomerName = ? , " +
                    "StateOrPrivate = ? WHERE id_customer = ?;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //creating new table
    public void createNewTable(String tableName, String sqlColumns) {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + sqlColumns + ");";
        try {
            statement.execute(sql);
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("WRONG INPUT DATA");
        }
    }

    //printing projects list with sum of all developers salary
    public void getSumOfProjectSalary(int projId) {

        String sql = "SELECT developer_projects.id_project as id_proj, sum(developers.salary) AS SumOfSalary " +
                "FROM developers, developer_projects " +
                "WHERE developers.id_dev IN ( SELECT DISTINCT developer_projects.id_dev " +
                "where developer_projects.id_project = " + projId + ");";

        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id_proj");
                int sum = rs.getInt("SumOfSalary");
                System.out.println("project id = " + id + " Sum of salary = " + sum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //printing developers working on project with Id
    public void getProjectDevelopers(int projNumber) {
        String sql = "SELECT DISTINCT developers.firstName, developers.secondaryName " +
                "FROM developers, developer_projects " +
                "WHERE  developers.id_dev IN (" +
                "SELECT developer_projects.id_dev " +
                "WHERE (developer_projects.id_project = " + projNumber + "));";
        getDevelopersList(sql);
    }

    //printing all java developers
    public void getJavaDevelopers() {
        String sql = "select DISTINCT developers.firstName, developers.secondaryName " +
                "from developers, developer_skill " +
                "where  developers.id_dev IN (" +
                "select  developer_skill.id_dev " +
                "where (developer_skill.id_skill in (select id_skill from skills where branch like 'Java' )));";
        getDevelopersList(sql);
    }

    //printing all middle developers
    public void getMiddleDevelopers() {
        String sql = "select DISTINCT developers.firstName, developers.secondaryName " +
                "from developers, developer_skill " +
                "where  developers.id_dev IN (" +
                "select  developer_skill.id_dev " +
                "where (developer_skill.id_skill in (select id_skill from skills where skill_level like 'Middle')));";
        getDevelopersList(sql);
    }

    //gets developer firstname and secondaryname depending on sql query
    private void getDevelopersList(String sql) {
        List<String> result = new ArrayList<>();

        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.add(rs.getString("firstName") + " " + rs.getString("secondaryName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        printResult(result);
    }

    //printing projects list and amount of developers working on it
    public void getProjectsInfo() {
        List<String> result = new ArrayList<>();
        String sql = "SELECT cost, ProjectName, count(developer_projects.id_dev) AS DevelopersCount " +
                "FROM projects, developer_projects " +
                "WHERE projects.id_project = developer_projects.id_project " +
                "GROUP BY projects.id_project;";
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.add(rs.getInt("cost") + "\t- \t" + rs.getString("ProjectName") +
                        " - " + rs.getInt("DevelopersCount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        printResult(result);
    }

    private void printResult(List<String> result) {         //printing lists to console
        for (String singleResult : result
                ) {
            System.out.println(singleResult);
        }
    }

    //creating new project
    public void addNewProject(String projectName, String description, int cost) {
        try {
            addProjectSt.setString(1, projectName);
            addProjectSt.setString(2, description);
            addProjectSt.setInt(3, cost);
            addProjectSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //creating new developer
    public void addNewDeveloper(String firstName, String secondaryName, int age, String gender, long salary) {
        try {
            addDeveloperSt.setString(1, firstName);
            addDeveloperSt.setString(2, secondaryName);
            addDeveloperSt.setInt(3, age);
            addDeveloperSt.setString(4, gender);
            addDeveloperSt.setLong(5, salary);
            addDeveloperSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //creating new customer
    public void addNewCustomer(String customerName, boolean stateOrPrivate) {
        try {
            addCustomerSt.setString(1, customerName);
            addCustomerSt.setBoolean(2, stateOrPrivate);
            addCustomerSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //delete developer with id
    public void deleteDeveloper(int id) {       //delete all connections and then delete developer
        try {
            deleteDeveloperProjectSt.setInt(1, 0);
            deleteDeveloperProjectSt.setInt(2, id);
            deleteDeveloperSkillSt.setInt(1, 0);
            deleteDeveloperSkillSt.setInt(2, id);
            deleteDeveloperSt.setInt(1, id);
            deleteDeveloperProjectSt.executeUpdate();
            deleteDeveloperSkillSt.executeUpdate();
            deleteDeveloperSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //delete project with id
    public void deleteProject(int id) {        //удаляет сначало все связи в связующих таблицах, потом уже саму запись
        try {
            deleteDeveloperProjectSt.setInt(1, id);
            deleteDeveloperProjectSt.setInt(2, 0);
            deleteCompaniesProjectsSt.setInt(1, 0);
            deleteCompaniesProjectsSt.setInt(2, id);
            deleteCustomersProjectsSt.setInt(1, 0);
            deleteCustomersProjectsSt.setInt(2, id);
            deleteProjectSt.setInt(1, id);
            deleteDeveloperProjectSt.executeUpdate();
            deleteCompaniesProjectsSt.executeUpdate();
            deleteCustomersProjectsSt.executeUpdate();
            deleteProjectSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //delete customer with id
    public void deleteCustomer(int id) {       //удаляет сначало все связи в связующих таблицах, потом уже саму запись
        try {
            deleteCustomersProjectsSt.setInt(1, id);
            deleteCustomersProjectsSt.setInt(2, 0);
            deleteCustomerSt.setInt(1, id);
            deleteCustomersProjectsSt.executeUpdate();
            deleteCustomerSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //update project with id, takes all required project fields
    public void updateProject(int id_project, String projectName, String description, int cost) {
        try {
            updateProjectSt.setString(1, projectName);
            updateProjectSt.setString(2, description);
            updateProjectSt.setInt(3, cost);
            updateProjectSt.setInt(4, id_project);
            updateProjectSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //update customer with id, takes all required customer fields
    public void updateCustomer(String customerName, boolean stOrPr, int id_customer) {
        try {
            updateCustomerSt.setString(1, customerName);
            updateCustomerSt.setBoolean(2, stOrPr);
            updateCustomerSt.setInt(3, id_customer);
            updateCustomerSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //update developer with id, takes all required developer fields
    public void updateDeveloper(int id_dev, String firstName, String secondaryName, int age, String gender, long salary)
    {
        try {
            updateDeveloperSt.setString(1, firstName);
            updateDeveloperSt.setString(2, secondaryName);
            updateDeveloperSt.setInt(3, age);
            updateDeveloperSt.setString(4, gender);
            updateDeveloperSt.setLong(5, salary);
            updateDeveloperSt.setInt(6, id_dev);
            updateDeveloperSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //closing connection, runs in the end of program using
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

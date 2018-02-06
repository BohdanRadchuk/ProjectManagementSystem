import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Functionality {

    private static final String connectionURL = "jdbc:mysql://localhost/homeworkonedb";
    private static final String user = "root";
    private static final String pass = "root";

    public Connection connection;
    private Statement statement;

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
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(connectionURL, user, pass);
            statement = connection.createStatement();
            addProjectSt = connection.prepareStatement("insert into projects (ProjectName, description, cost) values (?, ?, ?);");
            addDeveloperSt = connection.prepareStatement("insert into developers (firstName, secondaryName, age, gender, salary) values (?, ?, ?, ?, ?);");
            addCustomerSt = connection.prepareStatement("insert into customers (CustomerName, StateOrPrivate) values (?, ?);");
            deleteDeveloperSt = connection.prepareStatement("delete from developers where id_dev = ?;");
            deleteDeveloperProjectSt = connection.prepareStatement("delete from developer_projects where id_project =? or id_dev = ?;");
            deleteDeveloperSkillSt = connection.prepareStatement("delete from developer_skill where id_skill = ? or id_dev = ?;");
            deleteProjectSt = connection.prepareStatement("delete from projects where id_project = ?;");
            deleteCompaniesProjectsSt = connection.prepareStatement("delete from companies_projects where id_company = ? or id_project = ?;");
            deleteCustomersProjectsSt = connection.prepareStatement("delete from customers_projects where id_customer = ? or id_project = ?;");
            deleteCustomerSt = connection.prepareStatement("delete from customers where id_customer = ?;");
            updateProjectSt = connection.prepareStatement("update projects set ProjectName = ? , description = ? , cost = ? where id_project = ?;");
            updateDeveloperSt = connection.prepareStatement("update developers set firstName = ? , secondaryName = ? , age = ?, gender = ?, salary = ? where id_dev = ?;");
            updateCustomerSt = connection.prepareStatement("update customers set CustomerName = ? , StateOrPrivate = ? where id_customer = ?;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


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

    public void getSumOfProjectSalary(int projNumber) {

        String sql = "SELECT developer_projects.id_project as id_proj , sum(developers.salary) AS SumOfSalary FROM developers, developer_projects " +
                "WHERE developers.id_dev IN ( SELECT DISTINCT developer_projects.id_dev where developer_projects.id_project = " + projNumber + ");";

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

    public void getProjectDevelopers(int projNumber) {
        String sql = "SELECT DISTINCT developers.firstName, developers.secondaryName " +
                "FROM developers, developer_projects " +
                "WHERE  developers.id_dev IN (" +
                "SELECT developer_projects.id_dev " +
                "WHERE (developer_projects.id_project = " + projNumber + "));";
        getDevelopersList(sql);
    }

    public void getJavaDevelopers() {
        String sql = "select DISTINCT developers.firstName, developers.secondaryName " +
                "from developers, developer_skill " +
                "where  developers.id_dev IN (" +
                "select  developer_skill.id_dev " +
                "where (developer_skill.id_skill in (select id_skill from skills where branch like 'Java' )));";
        getDevelopersList(sql);
    }

    public void getMiddleDevelopers() {
        String sql = "select DISTINCT developers.firstName, developers.secondaryName " +
                "from developers, developer_skill " +
                "where  developers.id_dev IN (" +
                "select  developer_skill.id_dev " +
                "where (developer_skill.id_skill in (select id_skill from skills where skill_level like 'Middle')));";
        getDevelopersList(sql);
    }

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

    public void getProjectsInfo() {
        List<String> result = new ArrayList<>();
        String sql = "select cost, ProjectName, count(developer_projects.id_dev) as DevelopersCount " +
                "from projects, developer_projects " +
                "where projects.id_project = developer_projects.id_project " +
                "group by projects.id_project;";
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

    private void printResult(List <String> result){         //выводит на экран полученые результаты
        for (String singleResult : result
                ) {
            System.out.println(singleResult);
        }
    }

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

    public void addNewDeveloper(String firstName, String secondaryName, int age, String gender, int salary) {
        try {
            addDeveloperSt.setString(1, firstName);
            addDeveloperSt.setString(2, secondaryName);
            addDeveloperSt.setInt(3, age);
            addDeveloperSt.setString(4, gender);
            addDeveloperSt.setInt(5, salary);
            addDeveloperSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewCustomer(String customerName, boolean stateOrPrivate) {
        try {
            addCustomerSt.setString(1, customerName);
            addCustomerSt.setBoolean(2, stateOrPrivate);
            addCustomerSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDeveloper(int id) {          //удаляет сначало все связи в связующих таблицах, потом уже саму запись
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

    public void updateProject(String projectName, String description, int cost, int id_project) {
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

    public void updateDeveloper(String firstName, String secondaryName, int age, String gender, int salary, int id_dev) {
        try {
            updateDeveloperSt.setString(1, firstName);
            updateDeveloperSt.setString(2, secondaryName);
            updateDeveloperSt.setInt(3, age);
            updateDeveloperSt.setString(4, gender);
            updateDeveloperSt.setInt(5, salary);
            updateDeveloperSt.setInt(6, id_dev);
            updateDeveloperSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

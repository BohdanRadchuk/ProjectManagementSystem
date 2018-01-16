import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class Main {
    private String connectionURL = "jdbc:mysql://localhost/homeworkonedb";
    private String user = "root";
    private String pass = "root";

    private Connection connection;
    private Statement statement;

    private PreparedStatement addProjectSt;
    private PreparedStatement addDeveloperSt;
    private PreparedStatement addCustomerSt;

    private PreparedStatement deleteDeveloperSt;
    private PreparedStatement updateSt;

    public Main() {
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
            deleteDeveloperSt = connection.prepareStatement("delete from developer_skill where id_dev = ?;" +
                                                                "delete from developer_projects where id_dev = ?;" +
                                                                "delete from developers where id_dev = ?;");

            // selectSt = connection.prepareStatement("");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void getSummOfProjectSalary(int projNumber) {

        String sql = "SELECT developer_projects.id_project as id_proj , sum(developers.salary) AS SummOfSalary FROM developers, developer_projects " +
                "WHERE developers.id_dev IN ( SELECT DISTINCT developer_projects.id_dev where developer_projects.id_project = " + projNumber + ");";

        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id_proj");
                int sum = rs.getInt("SummOfSalary");
                System.out.println("project id = " + id + " Summ of salary = " + sum);
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

    public void getDevelopersList(String sql) {
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
        for (String asd : result
                ) {
            System.out.println(asd);
        }
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
                result.add(rs.getInt("cost") + "\t- \t" + rs.getString("ProjectName") + " - " + rs.getInt("DevelopersCount") );
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
        for (String asd : result
                ) {
            System.out.println(asd);
        }
    }



    public void addNewProject (String projectName, String description, int cost){
        try {
            addProjectSt.setString(1, projectName);
            addProjectSt.setString(2, description);
            addProjectSt.setInt(3, cost);
            addProjectSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewDeveloper (String firstName, String secondaryName, int age, String gender, int salary){
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

    public void addNewCustomer (String customerName, boolean stateOrPrivate){
        try {
            addCustomerSt.setString(1, customerName);
            addCustomerSt.setBoolean(2, stateOrPrivate);
            addCustomerSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDeveloper ( int id){

        try {
            deleteDeveloperSt.setInt(1, id);
            deleteDeveloperSt.setInt(2, id);
            deleteDeveloperSt.setInt(3, id);
            deleteDeveloperSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        Main storage = new Main();
        System.out.println("Вывод зарплаты всех разработчиков отдельного проекта:");
        storage.getSummOfProjectSalary(2);
        System.out.println("Вывод списка разработчиков отдельного проекта:");
        storage.getProjectDevelopers(2);
        System.out.println("Вывод списка всех Java разработчиков");
        storage.getJavaDevelopers();
        System.out.println("Вывод списка всех middle  разработчиков");
        storage.getMiddleDevelopers();
        System.out.println("Вывод списка проэктов и количества разработчиков на них");
        storage.getProjectsInfo();
        //storage.addNewProject("newTestProject", "testtest", 500);             //creates new project
        //storage.addNewDeveloper("TestFirstName", "TestSecondary" , 22, "Male" , 600);       //creates new developer
        //storage.addNewCustomer("Customer", true);           //creates new Customer
        storage.deleteDeveloper( 3);
    }

}

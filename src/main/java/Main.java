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

    private PreparedStatement deleteDeveloperSt;
    private PreparedStatement selectSt;
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
           // selectSt = connection.prepareStatement("");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void getSummOfProjectSalary (int projNumber) {

        String sql = "SELECT  project_developer.id_project as id_proj , sum(developers.salary) AS SummOfSalary FROM developers, project_developer " +
                    "WHERE developers.id_dev IN ( SELECT DISTINCT project_developer.id_dev where project_developer.id_project = " + projNumber + ");";
        //String sql = "select * from developers;";


        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);

            while (rs.next()){
                int id = rs.getInt("id_proj");
                int sum  =  rs.getInt("SummOfSalary");
                System.out.println("project id = " + id + " Summ of salary = " +sum);
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
        String sql ="SELECT DISTINCT developers.firstName, developers.secondaryName " +
                "FROM developers, developer_projects " +
                "WHERE  developers.id_dev IN (" +
                "SELECT developer_projects.id_dev " +
                "WHERE (developer_projects.id_project = " + projNumber + "));";

        getDevelopersList(sql);
    }
    public void getJavaDevelopers(){
        String sql = "select DISTINCT developers.firstName, developers.secondaryName " +
                "from developers, developer_skill " +
                "where  developers.id_dev IN (" +
                "select  developer_skill.id_dev " +
                "where (developer_skill.id_skill in (select id_skill from skills where branch like 'Java' )));";
        getDevelopersList(sql);
    }

    public void getMiddleDevelopers(){
        String sql = "select DISTINCT developers.firstName, developers.secondaryName " +
                "from developers, developer_skill " +
                "where  developers.id_dev IN (" +
                "select  developer_skill.id_dev " +
                "where (developer_skill.id_skill in (select id_skill from skills where skill_level like 'Middle')));";
        getDevelopersList(sql);
    }

        public void getDevelopersList (String sql) {
        List <String> result = new ArrayList<>();

        ResultSet rs = null;
        try {
            rs  = statement.executeQuery(sql);
            while (rs.next()){
                result.add(rs.getString("firstName") + " " + rs.getString("secondaryName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (String asd: result
             ) {
            System.out.println(asd);
        };
    }



    public static void main(String[] args) {
        Main storage = new Main();
        System.out.println("Вывод зарплаты всех разработчиков отдельного проекта:");
        storage.getSummOfProjectSalary(2 );
        System.out.println("Вывод списка разработчиков отдельного проекта:");
        storage.getProjectDevelopers(2);
        System.out.println("Вывод списка всех Java разработчиков");
        storage.getJavaDevelopers();
        System.out.println("Вывод списка всех middle  разработчиков");
        storage.getMiddleDevelopers();
    }

}

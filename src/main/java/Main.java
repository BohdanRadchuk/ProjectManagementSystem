import java.sql.*;
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
            selectSt = connection.prepareStatement("select  project_developer.id_project , avg (developers.salary) as SummOfSalary" +
                        "from developers, project_developer, projects where developers.id_dev IN (" +
                        "select DISTINCT project_developer.id_dev where (project_developer.id_project = ?));");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getSummOfProjectSalary () {
        ResultSet rs = null;

        try {
            rs = selectSt.executeQuery();
            System.out.println(rs);
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


    public static void main(String[] args) {
        Main storage = new Main();
        storage.getSummOfProjectSalary();
    }

}

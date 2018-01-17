import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static final String connectionURL = "jdbc:mysql://localhost/homeworkonedb";
    private static final String user = "root";
    private static final String pass = "root";

    private Connection connection;
    private Statement statement;

    //preparedStatements for add operations
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
    private Scanner scanner = new Scanner(System.in);

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

    private void createNewTable(String tableName, String sqlColumns) {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + sqlColumns + ");";
        try {
            statement.execute(sql);
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("WRONG INPUT DATA");
        }
    }

    private void getSumOfProjectSalary(int projNumber) {

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

    private void getProjectDevelopers(int projNumber) {
        String sql = "SELECT DISTINCT developers.firstName, developers.secondaryName " +
                "FROM developers, developer_projects " +
                "WHERE  developers.id_dev IN (" +
                "SELECT developer_projects.id_dev " +
                "WHERE (developer_projects.id_project = " + projNumber + "));";
        getDevelopersList(sql);
    }

    private void getJavaDevelopers() {
        String sql = "select DISTINCT developers.firstName, developers.secondaryName " +
                "from developers, developer_skill " +
                "where  developers.id_dev IN (" +
                "select  developer_skill.id_dev " +
                "where (developer_skill.id_skill in (select id_skill from skills where branch like 'Java' )));";
        getDevelopersList(sql);
    }

    private void getMiddleDevelopers() {
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

    private void getProjectsInfo() {
        List<String> result = new ArrayList<>();
        String sql = "select cost, ProjectName, count(developer_projects.id_dev) as DevelopersCount " +
                "from projects, developer_projects " +
                "where projects.id_project = developer_projects.id_project " +
                "group by projects.id_project;";
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.add(rs.getInt("cost") + "\t- \t" + rs.getString("ProjectName") + " - " + rs.getInt("DevelopersCount"));
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

    private void addNewProject(String projectName, String description, int cost) {
        try {
            addProjectSt.setString(1, projectName);
            addProjectSt.setString(2, description);
            addProjectSt.setInt(3, cost);
            addProjectSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addNewDeveloper(String firstName, String secondaryName, int age, String gender, int salary) {
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

    private void addNewCustomer(String customerName, boolean stateOrPrivate) {
        try {
            addCustomerSt.setString(1, customerName);
            addCustomerSt.setBoolean(2, stateOrPrivate);
            addCustomerSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteDeveloper(int id) {          //удаляет сначало все связи в связующих таблицах, потом уже саму запись
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

    private void deleteProject(int id) {        //удаляет сначало все связи в связующих таблицах, потом уже саму запись
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

    private void deleteCustomer(int id) {       //удаляет сначало все связи в связующих таблицах, потом уже саму запись
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

    private void updateProject(String projectName, String description, int cost, int id_project) {
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

    private void updateCustomer(String customerName, boolean stOrPr, int id_customer) {
        try {
            updateCustomerSt.setString(1, customerName);
            updateCustomerSt.setBoolean(2, stOrPr);
            updateCustomerSt.setInt(3, id_customer);
            updateCustomerSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateDeveloper(String firstName, String secondaryName, int age, String gender, int salary, int id_dev) {
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

    private void startMenu(Main storage) {
        System.out.println("Введите номер операции");
        System.out.println("1 - Создать новые записи");
        System.out.println("2 - Считать даные из таблиц");
        System.out.println("3 - Изменить значения в таблицах");
        System.out.println("4 - Удалить записи из таблиц");
        System.out.println("0 - Выход");
        int choice = scanner.nextInt();
        if (choice == 1) createMenu(storage);

        if (choice == 2) readMenu(storage);

        if (choice == 3) updateMenu(storage);

        if (choice == 4) deleteMenu(storage);

        if (choice == 0) exitMenu(storage);
    }

    private void createMenu(Main storage) {
        System.out.println("Введите номер операции");
        System.out.println("1 - Создать новую таблицу");
        System.out.println("2 - Создать запись нового проэкта");
        System.out.println("3 - Создать запись нового разработчика");
        System.out.println("4 - Создать запись нового заказчика");
        System.out.println("0 - Выход");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("Введите название таблицы");
            scanner.nextLine();
            String tableName = scanner.nextLine();
            System.out.println("Введите все поля с определением всех параметров, например: id INT AUTO_INCREMENT PRIMARY KEY, firstName VARCHAR(100) NOT NULL ");
            String sqlColumns = scanner.nextLine();
            createNewTable(tableName, sqlColumns);
        }
        if (choice == 2) {
            System.out.println("введите имя проэкта");
            scanner.nextLine();
            String prName = scanner.nextLine();
            System.out.println("введите описание проэкта");
            String prDesc = scanner.nextLine();
            System.out.println("введите стоимость проэкта");
            int prCost = scanner.nextInt();
            storage.addNewProject(prName, prDesc, prCost);             //creates new project
        }
        if (choice == 3) {
            System.out.println("введите имя разработчика");
            scanner.nextLine();
            String devName = scanner.nextLine();
            System.out.println("введите фамилию разработчика");
            String devSecName = scanner.nextLine();
            System.out.println("введите возраст разработчика");
            int devAge = scanner.nextInt();
            System.out.println("введите пол разработчика");
            scanner.nextLine();
            String devGend = scanner.nextLine();
            System.out.println("введите зарплату разработчика");
            int devSalary = scanner.nextInt();
            storage.addNewDeveloper(devName, devSecName, devAge, devGend, devSalary);       //creates new developer
        }
        if (choice == 4) {
            System.out.println("введите имя заказчика");
            scanner.nextLine();
            String custName = scanner.nextLine();
            System.out.println("это государственный или частный заказчик ?");
            System.out.println("1 - государственный");
            System.out.println("2 - частный");
            int custStOrPr = scanner.nextInt();
            boolean stOrPr = true;
            if (custStOrPr == 1) stOrPr = true;
            if (custStOrPr == 2) stOrPr = false;
            storage.addNewCustomer(custName, stOrPr);           //creates new Customer
        }
        if (choice == 0) {
            startMenu(storage);
        }
        if (choice != 0) otherOperation(storage);
    }

    private void readMenu(Main storage) {
        System.out.println("Выберите данные которые хотите считать");
        System.out.println("1 - Вывод зарплаты всех разработчиков отдельного проекта:");
        System.out.println("2 - Вывод списка разработчиков отдельного проекта:");
        System.out.println("3 - Вывод списка всех Java разработчиков");
        System.out.println("4 - Вывод списка всех middle  разработчиков");
        System.out.println("5 - Вывод списка проэктов и количества разработчиков на них");
        System.out.println("0 - Выход из этого меню");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("Введите id проэкта");
            storage.getSumOfProjectSalary(scanner.nextInt());
        }
        if (choice == 2) {
            System.out.println("Введите id проэкта");
            storage.getProjectDevelopers(scanner.nextInt());
        }
        if (choice == 3) {
            storage.getJavaDevelopers();
        }
        if (choice == 4) {
            storage.getMiddleDevelopers();
        }
        if (choice == 5) {
            System.out.println("Стоимость проэкта -  название проэкта - количество разработчиков");
            getProjectsInfo();
        }
        if (choice == 0) {
            startMenu(storage);
        }
        if (choice != 0)
            otherOperation(storage);
    }

    private void updateMenu(Main storage) {
        System.out.println("Введите номер операции");
        System.out.println("1 - Изменить запись проэкта");
        System.out.println("2 - Изменить запись разработчика");
        System.out.println("3 - Изменить запись заказчика");
        System.out.println("0 - Выход");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("введите Id проэкта");
            int prId = scanner.nextInt();
            System.out.println("введите новое название проэкта");
            scanner.nextLine();
            String prName = scanner.nextLine();
            System.out.println("введите новое описание проэкта");
            String prDescr = scanner.nextLine();
            System.out.println("введите новую стоимость проэкта");
            int prCost = scanner.nextInt();
            storage.updateProject(prName, prDescr, prCost, prId);
        }
        if (choice == 2) {
            System.out.println("введите Id разработчика");
            int devId = scanner.nextInt();
            System.out.println("введите новое имя разработчика");
            scanner.nextLine();
            String devName = scanner.nextLine();
            System.out.println("введите новую фамилию разработчика");
            String devSecName = scanner.nextLine();
            System.out.println("введите новый возраст разработчика");
            int devAge = scanner.nextInt();
            System.out.println("введите новый пол разработчика");
            scanner.nextLine();
            String devGend = scanner.nextLine();
            System.out.println("введите новый зарплату разработчика");
            int devSalary = scanner.nextInt();
            storage.updateDeveloper(devName, devSecName, devAge, devGend, devSalary, devId);
        }
        if (choice == 3) {
            System.out.println("введите Id заказчика");
            int custId = scanner.nextInt();
            System.out.println("введите новое имя заказчика");
            scanner.nextLine();
            String custName = scanner.nextLine();
            System.out.println("это государственный или частный заказчик ?");
            System.out.println("1 - государственный");
            System.out.println("2 - частный");
            int custStOrPr = scanner.nextInt();
            boolean stOrPr = true;
            if (custStOrPr == 1) stOrPr = true;
            if (custStOrPr == 2) stOrPr = false;
            storage.updateCustomer(custName, stOrPr, custId);
        }
        if (choice == 0) {
            startMenu(storage);
        }
        if (choice != 0) otherOperation(storage);
    }

    private void deleteMenu(Main storage) {
        System.out.println("Введите номер операции");
        System.out.println("1 - Удалить запись проэкта");
        System.out.println("2 - Удалить запись разработчика");
        System.out.println("3 - Удалить запись заказчика");
        System.out.println("0 - Выход");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("введите Id проэкта");
            int prId = scanner.nextInt();
            storage.deleteProject(prId);
        }
        if (choice == 2) {
            System.out.println("введите Id разработчика");
            int devId = scanner.nextInt();
            storage.deleteDeveloper(devId);
        }
        if (choice == 3) {
            System.out.println("введите Id заказчика");
            int custId = scanner.nextInt();
            storage.deleteCustomer(custId);
        }
        if (choice == 0) {
            startMenu(storage);
        }
        if (choice != 0) otherOperation(storage);
    }

    private void otherOperation(Main storage) {      //меню возврата в главное меню или выхода (используется после операций)
        System.out.println("хотите выполнить другую операцию ?");
        System.out.println("1 - Да, вернуться в главное меню");
        System.out.println("0 - Нет, закрыть програму");
        int choice = scanner.nextInt();
        if (choice == 1 || choice == 0) {
            if (choice == 1) {
                startMenu(storage);
            }
            if (choice == 0) {
                exitMenu(storage);
            }
        } else {
            System.out.println("Введите один из предложеных вариантов");
            otherOperation(storage);
        }
    }

    private void exitMenu(Main storage) {        //меню выхода для закрытия соединения
        try {
            storage.connection.close();
            System.out.println("Спасибо что пользовались нашей програмой");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Main storage = new Main();
        storage.startMenu(storage);
        //storage.addNewProject("newTestProject", "testtest", 500);             //creates new project
        //storage.addNewDeveloper("TestFirstName", "TestSecondary" , 22, "Male" , 600);       //creates new developer
        //storage.addNewCustomer("Customer", true);           //creates new Customer
    }

}

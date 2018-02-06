import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleInterface {
    Scanner scanner = new Scanner(System.in);
    Functionality storage = new Functionality();
    public void startMenu() {
        System.out.println("Введите номер операции");
        System.out.println("1 - Создать новые записи");
        System.out.println("2 - Считать даные из таблиц");
        System.out.println("3 - Изменить значения в таблицах");
        System.out.println("4 - Удалить записи из таблиц");
        System.out.println("0 - Выход");
        int choice = scanner.nextInt();
        if (choice == 1) createMenu();

        if (choice == 2) readMenu();

        if (choice == 3) updateMenu();

        if (choice == 4) deleteMenu();

        if (choice == 0) exitMenu();
    }

    private void createMenu() {
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
            storage.createNewTable(tableName, sqlColumns);
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
            startMenu();
        }
        if (choice != 0) otherOperation();
    }

    private void readMenu() {
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
            storage.getProjectsInfo();
        }
        if (choice == 0) {
            startMenu();
        }
        if (choice != 0)
            otherOperation();
    }

    private void updateMenu() {
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
            startMenu();
        }
        if (choice != 0) otherOperation();
    }

    private void deleteMenu() {
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
            startMenu();
        }
        if (choice != 0) otherOperation();
    }

    private void otherOperation() {      //меню возврата в главное меню или выхода (используется после операций)
        System.out.println("хотите выполнить другую операцию ?");
        System.out.println("1 - Да, вернуться в главное меню");
        System.out.println("0 - Нет, закрыть програму");
        int choice = scanner.nextInt();
        if (choice == 1 || choice == 0) {
            if (choice == 1) {
                startMenu();
            }
            if (choice == 0) {
                exitMenu();
            }
        } else {
            System.out.println("Введите один из предложеных вариантов");
            otherOperation();
        }
    }
    private void exitMenu() {        //меню выхода для закрытия соединения
        try {
            storage.connection.close();
            System.out.println("Спасибо что пользовались нашей програмой");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

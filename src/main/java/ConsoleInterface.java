import hibernateFunctionality.HibernateFactory;
import hibernateFunctionality.HibernateFunctionality;
import jdbc.Functionality;

import java.util.Scanner;

public class ConsoleInterface {
    private Scanner scanner = new Scanner(System.in);
    private HibernateFunctionality hibFunc;
    private Functionality storage;
    private boolean hibOrJDBC;

    public ConsoleInterface(boolean hibOrJDBC) {        //принимаем булеан переменную и создаем ресурс для работы с базой
        this.hibOrJDBC = hibOrJDBC;
        if (hibOrJDBC) {
            hibFunc = new HibernateFunctionality();     //если будем использовать хибернейт
        } else storage = new Functionality();             //если будем использовать jdbc
    }

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
            if (hibOrJDBC) {
                System.out.println("Извините эта функция не поддерживается этим типом соединения, исользуйте JDBC");
            } else {
                System.out.println("Введите название таблицы");

                scanner.nextLine();
                String tableName = scanner.nextLine();
                System.out.println("Введите все поля с определением всех параметров, например: id INT AUTO_INCREMENT PRIMARY KEY, firstName VARCHAR(100) NOT NULL ");
                String sqlColumns = scanner.nextLine();
                if (hibOrJDBC) {                                                 //перед выполнением любого запроса проверка не тип используемой связи
                    //hibFunc.hibCreateNewTable(tableName, sqlColumns);         //запрос происходит, но на базе не отображается никак
                } else storage.createNewTable(tableName, sqlColumns);
            }
        }
        if (choice == 2) {
            System.out.println("введите имя проэкта");
            scanner.nextLine();
            String prName = scanner.nextLine();
            System.out.println("введите описание проэкта");
            String prDesc = scanner.nextLine();
            System.out.println("введите стоимость проэкта");
            int prCost = scanner.nextInt();
            if (hibOrJDBC) {
                hibFunc.hibCreateNewProject(prName, prDesc, prCost);
            } else storage.addNewProject(prName, prDesc, prCost);             //creates new project
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
            long devSalary = scanner.nextLong();
            if (hibOrJDBC) {
                hibFunc.hibCreateNewDeveloper(devName, devSecName, devAge, devGend, devSalary);
            } else
                storage.addNewDeveloper(devName, devSecName, devAge, devGend, devSalary);       //creates new developer
        }
        if (choice == 4) {
            System.out.println("введите имя заказчика");
            scanner.nextLine();
            String custName = scanner.nextLine();
            System.out.println("это государственный или частный заказчик ?");
            System.out.println("1 - государственный");
            System.out.println("0 - частный");
            byte custStOrPr = scanner.nextByte();

            if (hibOrJDBC) {
                hibFunc.hibCreateNewCustomer(custName, custStOrPr);
            } else {
                boolean stOrPr = true;
                if (custStOrPr == 1) stOrPr = true;
                if (custStOrPr == 0) stOrPr = false;
                storage.addNewCustomer(custName, stOrPr);           //creates new Customer
            }
        }
        if (choice == 0) {
            startMenu();
        } else otherOperation();
    }

    private void readMenu() {
        System.out.println("Выберите данные которые хотите считать");
        System.out.println("1 - Вывод зарплаты всех разработчиков отдельного проекта:");
        System.out.println("2 - Вывод списка разработчиков отдельного проекта:");
        System.out.println("3 - Вывод списка всех Java разработчиков");
        System.out.println("4 - Вывод списка всех middle разработчиков");
        System.out.println("5 - Вывод списка проэктов и количества разработчиков на них");
        System.out.println("0 - Выход из этого меню");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("Введите id проэкта");
            int prId = scanner.nextInt();
            if (hibOrJDBC) {
                hibFunc.hbGetSumOfProjectSalary(prId);
            } else storage.getSumOfProjectSalary(prId);
        }
        if (choice == 2) {
            System.out.println("Введите id проэкта");
            int prId = scanner.nextInt();
            if (hibOrJDBC) {
                hibFunc.hbGetDevelopersOfProject(prId);
            } else storage.getProjectDevelopers(scanner.nextInt());
        }
        if (choice == 3) {
            if (hibOrJDBC) {
                hibFunc.hbGetJavaDevelopers();
            } else storage.getJavaDevelopers();
        }
        if (choice == 4) {
            if (hibOrJDBC) {
                hibFunc.hbGetMiddleDevelopers();
            } else storage.getMiddleDevelopers();
        }
        if (choice == 5) {
            System.out.println("Стоимость проэкта -  название проэкта - количество разработчиков");
            if (hibOrJDBC) {
                hibFunc.hbGetProjectsInfo();
            } else storage.getProjectsInfo();
        }
        if (choice == 0) {
            startMenu();
        } else otherOperation();
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
            if (hibOrJDBC) {
                hibFunc.hibUpdateProject(prId, prName, prDescr, prCost);
            } else storage.updateProject(prId, prName, prDescr, prCost);
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
            if (hibOrJDBC) {
                hibFunc.hibUpdateDeveloper(devId, devName, devSecName, devAge, devGend, devSalary);
            } else storage.updateDeveloper(devId, devName, devSecName, devAge, devGend, devSalary);
        }
        if (choice == 3) {
            System.out.println("введите Id заказчика");
            int custId = scanner.nextInt();
            System.out.println("введите новое имя заказчика");
            scanner.nextLine();
            String custName = scanner.nextLine();
            System.out.println("это государственный или частный заказчик ?");
            System.out.println("1 - государственный");
            System.out.println("0 - частный");
            byte custStOrPr = scanner.nextByte();

            if (hibOrJDBC) {
                hibFunc.hibUpdateCustomer(custId, custName, custStOrPr);
            } else {
                boolean stOrPr = true;
                if (custStOrPr == 1) stOrPr = true;
                if (custStOrPr == 0) stOrPr = false;
                storage.updateCustomer(custName, stOrPr, custId);
            }
        }
        if (choice == 0) {
            startMenu();
        } else otherOperation();
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
            if (hibOrJDBC) {
                hibFunc.hibDeleteProject(prId);
            } else storage.deleteProject(prId);
        }
        if (choice == 2) {
            System.out.println("введите Id разработчика");
            int devId = scanner.nextInt();
            if (hibOrJDBC) {
                hibFunc.hibDeleteDeveloper(devId);
            } else storage.deleteDeveloper(devId);
        }
        if (choice == 3) {
            System.out.println("введите Id заказчика");
            int custId = scanner.nextInt();
            if (hibOrJDBC) {
                hibFunc.hibDeleteCustomer(custId);
            } else storage.deleteCustomer(custId);
        }
        if (choice == 0) {
            startMenu();
        } else otherOperation();
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
        if (hibOrJDBC) {
            HibernateFactory.shutdown();
        } else storage.closeConnection();
        System.out.println("Спасибо что пользовались нашей програмой");
    }
}

import JDBC.ConsoleInterface;

import java.util.Scanner;


public class Main {


    //preparedStatements for add operations

    private Scanner scanner = new Scanner(System.in);








    public static void main(String[] args) {
        Main storage = new Main();
        ConsoleInterface ci = new ConsoleInterface();
        ci.startMenu();
        //storage.startMenu(storage);
        //storage.addNewProject("newTestProject", "testtest", 500);             //creates new project
        //storage.addNewDeveloper("TestFirstName", "TestSecondary" , 22, "Male" , 600);       //creates new developer
        //storage.addNewCustomer("Customer", true);           //creates new Customer
    }

}

import dao.hibernate.HibernateDeveloperDAOImpl;
import jdbc.ConsoleInterface;


public class Main {

    public static void main(String[] args) {

        ConsoleInterface ci = new ConsoleInterface();
        //ci.startMenu();
        HibernateDeveloperDAOImpl hibDevDaoImpl = new HibernateDeveloperDAOImpl();
        hibDevDaoImpl.getAll();
        //storage.startMenu(storage);
        //storage.addNewProject("newTestProject", "testtest", 500);             //creates new project
        //storage.addNewDeveloper("TestFirstName", "TestSecondary" , 22, "Male" , 600);       //creates new developer
        //storage.addNewCustomer("Customer", true);           //creates new Customer
    }

}

import dao.hibernate.HibernateDeveloperDAOImpl;
import entities.Developer;
import entities.Skills;
import jdbc.ConsoleInterface;

import java.math.BigDecimal;


public class Main {

    public static void main(String[] args) {

        ConsoleInterface ci = new ConsoleInterface();
        //ci.startMenu();
        HibernateDeveloperDAOImpl hibDevDaoImpl = new HibernateDeveloperDAOImpl();
        System.out.println(hibDevDaoImpl.getAll());
        Developer developer = new Developer();
        developer.setId(1);
        developer.setFirstName("NEIVANa");
        developer.setSecondaryName("Karinova");
        developer.setAge(22);
        developer.setSalary(BigDecimal.valueOf(4850L) );
        developer.setGender("female");

        hibDevDaoImpl.update(developer);



        //storage.startMenu(storage);
        //storage.addNewProject("newTestProject", "testtest", 500);             //creates new project
        //storage.addNewDeveloper("TestFirstName", "TestSecondary" , 22, "Male" , 600);       //creates new developer
        //storage.addNewCustomer("Customer", true);           //creates new Customer
    }

}

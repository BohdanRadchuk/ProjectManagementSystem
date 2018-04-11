import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Для использования в работе \nHibernate - введите 1");
        System.out.println("JDBC - введите 2");
        boolean hibOrJdbc = true;
        int choice = scanner.nextInt();
        if (choice == 1 || choice == 2) {               //choosing usage jdbc or hibernate
            if (choice == 1) {
                hibOrJdbc = true;
            }
            if (choice == 2) {
                hibOrJdbc = false;
            }
        } else System.out.println("Wrong input data, default usage - hibernate");
        ConsoleInterface ci = new ConsoleInterface(hibOrJdbc);
        ci.startMenu();
    }
}

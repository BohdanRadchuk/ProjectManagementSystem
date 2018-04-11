package anotationServlets.read;

import anotationServlets.managers.CustomerManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/getAllCustomers")
public class GetAllCustomers extends HttpServlet {
    private CustomerManager customerManager = new CustomerManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        customerManager.customerReadGet(req, resp);
    }
}



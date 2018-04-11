package anotationServlets.delete;

import anotationServlets.managers.CustomerManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteCustomer")
public class DeleteCustomer extends HttpServlet {
    private CustomerManager customerManager = new CustomerManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        customerManager.customerReadGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        customerManager.customerDeletePost(req, resp);
    }
}


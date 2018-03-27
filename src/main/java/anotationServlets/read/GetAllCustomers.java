package anotationServlets.read;

import dao.hibernate.HibernateCustomersDAOImpl;
import dao.hibernate.HibernateDeveloperDAOImpl;
import entities.Customers;
import entities.Developer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/getAllCustomers")
public class GetAllCustomers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateCustomersDAOImpl hbCustImpl = new HibernateCustomersDAOImpl();
        List<Customers> customers = hbCustImpl.getAll();

        req.setAttribute("customers", customers);
        req.getRequestDispatcher("view/read/readAllCustomers.jsp").forward(req, resp);
    }
}



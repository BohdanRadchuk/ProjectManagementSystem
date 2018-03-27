package anotationServlets.delete;

import dao.hibernate.HibernateCustomersDAOImpl;
import entities.Customers;
import hibernateFunctionality.HibernateFunctionality;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/deleteCustomer")
public class DeleteCustomer extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateCustomersDAOImpl hbCustImpl = new HibernateCustomersDAOImpl();
        List<Customers> customers = hbCustImpl.getAll();

        req.setAttribute("customers", customers);
        req.getRequestDispatcher("view/read/readAllCustomers.jsp").forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("customerId");
        int id = Integer.parseInt(param);
        System.out.println(id);
        HibernateFunctionality hbFunc = new HibernateFunctionality();
        hbFunc.hibDeleteCustomer(id);

        resp.sendRedirect("http://localhost:8080/deleteCustomer?method=3");
    }

}


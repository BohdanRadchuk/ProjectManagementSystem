package anotationServlets.update;

import dao.hibernate.HibernateCustomersDAOImpl;
import dao.hibernate.HibernateProjectsDAOImpl;
import entities.Customers;
import entities.Projects;
import hibernateFunctionality.HibernateFunctionality;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/updateCustomer")
public class UpdateCustomer extends HttpServlet {
private static final int DO_UPDATE = -1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateCustomersDAOImpl hbCustImpl = new HibernateCustomersDAOImpl();
        List<Customers> customers= hbCustImpl.getAll();

        req.setAttribute("customers", customers);
        req.getRequestDispatcher("view/read/readAllCustomers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("customerId");
        int id = Integer.parseInt(param);

        HibernateFunctionality hbFunc = new HibernateFunctionality();
        if (id != DO_UPDATE) {
            req.setAttribute("idCustomer", id);
            req.getRequestDispatcher("view/update/updateCustomer.jsp").forward(req, resp);

        } else {

            for (String paramName : req.getParameterMap().keySet()) {
                System.out.println(paramName + "=" + req.getParameter(paramName));
            }
            HibernateFunctionality hibFunc = new HibernateFunctionality();

            int idCust = Integer.parseInt(req.getParameter("custId"));
            System.out.println("idCust = " + idCust);
            String custName = req.getParameter("custName");
            byte stOrPr = Byte.valueOf(req.getParameter("stOrPr"));
            System.out.println(stOrPr);
            hibFunc.hibUpdateCustomer(idCust, custName, stOrPr);
            resp.sendRedirect("http://localhost:8080/updateCustomer?method=2");  //need to update url
        }
    }
}

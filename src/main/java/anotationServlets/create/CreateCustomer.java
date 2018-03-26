package anotationServlets.create;

import hibernateFunctionality.HibernateFunctionality;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createCust")
public class CreateCustomer extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/create/createCustomer.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (String paramName : req.getParameterMap().keySet()) {
            System.out.println(paramName + "=" + req.getParameter(paramName));
        }
        HibernateFunctionality hibFunc = new HibernateFunctionality();

        String custName = req.getParameter("custName");
        byte stOrPr = Byte.valueOf(req.getParameter("stOrPr"));
        System.out.println(stOrPr);
        hibFunc.hibCreateNewCustomer(custName,stOrPr);
        resp.sendRedirect("http://localhost:8080/createCust");
    }
}

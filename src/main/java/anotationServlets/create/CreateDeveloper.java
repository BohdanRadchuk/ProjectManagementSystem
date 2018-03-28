package anotationServlets.create;

import hibernateFunctionality.HibernateFunctionality;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/createDev")
public class CreateDeveloper extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/create/createDeveloper.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (String paramName : req.getParameterMap().keySet()) {
            System.out.println(paramName + "=" + req.getParameter(paramName));
        }
        HibernateFunctionality hibFunc = new HibernateFunctionality();

        String devFirstName = req.getParameter("firstName");
        String devSecName = req.getParameter("secondaryName");
        int age =  Integer.valueOf(req.getParameter("age"));
        String gender = req.getParameter("gender");
        long salary = Long.valueOf(req.getParameter("salary"));

        hibFunc.hibCreateNewDeveloper(devFirstName, devSecName, age, gender, salary);
        resp.sendRedirect("http://localhost:8080/createDev");
    }
}

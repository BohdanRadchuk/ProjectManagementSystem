package anotationServlets.create;

import hibernateFunctionality.HibernateFunctionality;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createProject")
public class CreateProject extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/create/createProject.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (String paramName : req.getParameterMap().keySet()) {
            System.out.println(paramName + "=" + req.getParameter(paramName));
        }
        HibernateFunctionality hibFunc = new HibernateFunctionality();

        String prName = req.getParameter("projectName");
        String prDescr = req.getParameter("projectDescr");
        int prPrice =  Integer.valueOf(req.getParameter("projectPrice"));

        hibFunc.hibCreateNewProject(prName, prDescr, prPrice);
        resp.sendRedirect("http://localhost:8080/createProject");
    }
}
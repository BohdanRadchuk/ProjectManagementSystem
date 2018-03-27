package anotationServlets.delete;

import dao.hibernate.HibernateDeveloperDAOImpl;
import entities.Developer;
import hibernateFunctionality.HibernateFunctionality;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/deleteDeveloper")
public class DeleteDeveloper extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateDeveloperDAOImpl hbDevImpl = new HibernateDeveloperDAOImpl();
        List<Developer> developers= hbDevImpl.getAll();

        req.setAttribute("developers", developers);
        req.getRequestDispatcher("view/read/readDevelopers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("developerId");
        int id = Integer.parseInt(param);
        System.out.println(id);
        HibernateFunctionality hbFunc = new HibernateFunctionality();
        hbFunc.hibDeleteDeveloper(id);

        resp.sendRedirect("http://localhost:8080/deleteDeveloper?method=3");
    }

}

package anotationServlets.read;

import dao.hibernate.HibernateProjectsDAOImpl;
import entities.Developer;
import entities.Projects;
import hibernateFunctionality.HibernateFunctionality;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/getAllProjects")
public class GetAllProjects extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateProjectsDAOImpl hbPrImpl = new HibernateProjectsDAOImpl();
        List<Projects> projects = hbPrImpl.getAll();

        req.setAttribute("allProjects", projects);
        req.getRequestDispatcher("view/read/readAllProjectsList.jsp").forward(req, resp);
    }
}



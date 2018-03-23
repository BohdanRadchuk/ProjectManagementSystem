package anotationServlets.read;

import dao.hibernate.HibernateProjectsDAOImpl;
import entities.Projects;
import hibernateFunctionality.HibernateFunctionality;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getOneProjectDevelopers")
public class GetOneProjectDevelopers extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("view/read/readOneProjectDevelopers.jsp");
        HibernateProjectsDAOImpl hbPrImpl = new HibernateProjectsDAOImpl();
        List<Projects> projects = hbPrImpl.getAll();
    }
}

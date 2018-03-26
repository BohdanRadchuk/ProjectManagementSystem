package anotationServlets.read;

import dao.hibernate.HibernateProjectsDAOImpl;
import entities.ProjectInfo;
import entities.Projects;
import hibernateFunctionality.HibernateFunctionality;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getAllDevelopersOnProjects")
public class GetAllDevelopersOnProjects extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateFunctionality hbFunc = new HibernateFunctionality();
        List<ProjectInfo> projectInfos = hbFunc.hbGetProjectsInfo();

        req.setAttribute("projects", projectInfos);
        req.getRequestDispatcher("view/read/readAllDevelopersOnProjects.jsp").forward(req,resp);
    }
}
